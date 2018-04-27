package com.gl365.app.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl365.app.common.HttpParamConstant;
import com.gl365.app.common.PermisConstant;
import com.gl365.app.dto.ResultDto;
import com.gl365.app.enums.OperatorSignEnum;
import com.gl365.app.enums.ResultCodeEnum;
import com.gl365.app.remote.member.MemberRemoteService;
import com.gl365.app.remote.member.request.GetUserInfoDto;
import com.gl365.app.remote.member.request.GetUserPhoneReqDto;
import com.gl365.app.remote.member.request.UserUpdateDto;
import com.gl365.app.remote.merchant.MerchantRemoteService;
import com.gl365.app.remote.merchant.request.MerchantSaveOperatorReqDto;
import com.gl365.app.remote.merchant.request.MerchantUnBindOperatorReqDto;
import com.gl365.app.remote.merchant.request.MerchantUpdateOperatorReqDto;
import com.gl365.app.remote.merchant.response.MerchantOperatorDto;
import com.gl365.app.remote.validator.OperatorValidate;
import com.gl365.app.remote.validator.ValidateException;
import com.gl365.app.service.RedisService;
import com.gl365.app.service.StaffOperatorService;
import com.gl365.app.web.PermisController;

@Service
public class StaffOperatorServiceImpl implements StaffOperatorService {

	@SuppressWarnings("unused")
	private static Integer ADMIN = 1, SHOP_MANAGER = 2, OPERATOR = 3;

	@Autowired
	private RedisService redisService;

	@Autowired
	private MemberRemoteService memberRemoteService;
	
	@Autowired
	private MerchantRemoteService merchantRemoteService;

	@Override
	public Object addStaff(MerchantSaveOperatorReqDto req, HttpServletRequest request)
			throws ValidateException, Exception {

		OperatorValidate.validate(req);
		// 判断是否有管理员的权限
		ResultDto<?> permis = hasAdminPermis(request);
		if (!ResultCodeEnum.System.SUCCESS.getCode().equals(permis.getResult())) {
			return permis;
		} else {
			if (Boolean.TRUE.equals(req.getStaffmanagementBoo())) {
				if (!PermisConstant.ADMIN.equals(getOperator(request).getRoleId())) {
					return ResultDto.result(ResultCodeEnum.User.OPERATOR_MANAGE_SIGN_ERROR);
				}
			}
		}

		String merchantNo = getOperator(request).getMerchantNo();
		ResultDto<MerchantOperatorDto> result = merchantRemoteService
				.addStaff(new MerchantSaveOperatorReqDto(req, merchantNo));
		if (!ResultCodeEnum.System.SUCCESS.getCode().equals(result.getResult())) {
			return result;
		}
		MerchantOperatorDto operator = result.getData();
		redisService.set(operator.getOperatorId(), operator, PermisController.LIVE_TIME);
		return ResultDto.result(ResultCodeEnum.System.SUCCESS);
	}

	private MerchantOperatorDto getOperator(HttpServletRequest request) {
		// 没有直接返回Null 调用处抛系统异常,因为不存在这种情况
		Object userId = request.getSession().getAttribute(HttpParamConstant.Session.GL_APP_USER_ID);
		if (null == userId)
			return null;
		Object redis = redisService.get(userId.toString());
		if (null == redis || !(redis instanceof MerchantOperatorDto))
			return null;
		MerchantOperatorDto opr = (MerchantOperatorDto) redis;
		return opr;
	}

	private ResultDto<?> hasAdminPermis(HttpServletRequest request) {
		MerchantOperatorDto operator = getOperator(request);
		if (OperatorSignEnum.MANAGE_SIGN.getValue().equals(operator.getOperatorSign())
				|| OperatorSignEnum.CASHIER_MANAGE_SIGN.getValue().equals(operator.getOperatorSign())) {
			return ResultDto.result(ResultCodeEnum.System.SUCCESS);
		} else {
			if (PermisConstant.ADMIN.equals(operator.getRoleId())) {
				return ResultDto.result(ResultCodeEnum.System.SUCCESS);
			}
		}
		return ResultDto.result(ResultCodeEnum.User.OPERATOR_MANAGE_SIGN_ERROR);
	}

	@Override
	public Object saveOperatorcheck(GetUserPhoneReqDto req) throws Exception{

		if (StringUtils.isBlank(req.getPhone()))
			return ResultDto.result(ResultCodeEnum.System.PARAM_ERROR, "员工手机号不能为空", null);
		Map<String, Object> result = new HashMap<String, Object>();
		UserUpdateDto data = memberRemoteService.getUserInfoByMobilePhone(new GetUserInfoDto(req.getPhone()));
		if (null == data)
			return ResultDto.result(ResultCodeEnum.User.OPERATOR_NOT_MEMBER);
		result.put("userId", data.getUserId());
		result.put("name", data.getRealName());
		result.put("idCard", data.getCertNum());
		result.put("phone", data.getMobilePhone());
		result.put("authStatus", data.getAuthStatus());
		return ResultDto.result(ResultCodeEnum.System.SUCCESS, result);
	}

	@Override
	public Object updateOperator(MerchantUpdateOperatorReqDto req, HttpServletRequest request)
			throws ValidateException,Exception {

		OperatorValidate.validate(req);
		MerchantOperatorDto oldOperator = merchantRemoteService.getOperatorById(req.getOperatorId()).getData();
		if (null == oldOperator) {
			return ResultDto.result(ResultCodeEnum.System.SYSTEM_DATA_EXECEPTION);
		}
		// 判断前端是否修改过员工管理权限
		boolean flag = changeStaffmanagementBoo(oldOperator, req);
		// 判断是否有管理员的权限
		ResultDto<?> permis = hasAdminPermis(request);
		if (!ResultCodeEnum.System.SUCCESS.getCode().equals(permis.getResult())) {
			return permis;
		} else {
			if (flag && (!PermisConstant.ADMIN.equals(getOperator(request).getRoleId()))) {
				return ResultDto.result(ResultCodeEnum.User.OPERATOR_MANAGE_SIGN_ERROR);
			}
		}
		ResultDto<MerchantOperatorDto> result = merchantRemoteService.updateOperator(req);
		MerchantOperatorDto operator = result.getData();
		if (!ResultCodeEnum.System.SUCCESS.getCode().equals(result.getResult()))
			return result;
		// 店长降级为操作员时清除登录状态
		if (null != oldOperator && SHOP_MANAGER.equals(oldOperator.getRoleId()) && OPERATOR.equals(req.getRoleID())) {
			redisService.del(oldOperator.getOperatorId());
			// 清掉以前的的token
			// userHandler.cleanToken(command.getOperatorId());
		} else
			redisService.set(operator.getOperatorId(), operator, PermisController.LIVE_TIME);
		return ResultDto.result(ResultCodeEnum.System.SUCCESS);
	}

	private boolean changeStaffmanagementBoo(MerchantOperatorDto oldOperator, MerchantUpdateOperatorReqDto command) {
		// 操作标志：0无1收银 2员工管理3收银和员工管理 9999管理员(管理员不收该权限限制)
		int operatorSign = oldOperator.getOperatorSign();
		boolean rlt = true;
		if (Boolean.TRUE.equals(command.getStaffmanagementBoo())) {
			if (operatorSign == 2 || operatorSign == 3 || operatorSign == 9999) {
				rlt = false;
			}
		} else if (Boolean.FALSE.equals(command.getStaffmanagementBoo())) {
			if (operatorSign == 0 || operatorSign == 1) {
				rlt = false;
			}
		}
		return rlt;
	}

	@Override
	public Object unBindOperator(MerchantUnBindOperatorReqDto req, HttpServletRequest request) throws Exception{

		if (StringUtils.isEmpty(req.getOperatorId())) return ResultDto.result(ResultCodeEnum.System.PARAM_NULL);
		// 除了管理员不可自我解绑,其它权限的人都可以调用
		MerchantOperatorDto curOperator = getOperator(request);
		if (req.getOperatorId().equals(curOperator) && PermisConstant.ADMIN.equals(curOperator.getRoleId())) {
			return ResultDto.result(ResultCodeEnum.System.PARAM_ERROR, "管理员不能解绑", null);
		}
		ResultDto<MerchantOperatorDto> result = merchantRemoteService.unBindOperator(req);
		if (!ResultCodeEnum.System.SUCCESS.getCode().equals(result.getResult())) return result;
		MerchantOperatorDto operator = result.getData();
		redisService.set(operator.getOperatorId(), operator, PermisController.LIVE_TIME);
		return ResultDto.result(ResultCodeEnum.System.SUCCESS);
	}

}
