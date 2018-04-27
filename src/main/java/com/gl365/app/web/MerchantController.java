package com.gl365.app.web;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.app.common.HttpParamConstant;
import com.gl365.app.common.Permis;
import com.gl365.app.common.PermisConstant;
import com.gl365.app.common.UserHandler;
import com.gl365.app.dto.ResultDto;
import com.gl365.app.enums.OperatorSignEnum;
import com.gl365.app.enums.QuitTypeEnum;
import com.gl365.app.enums.ResultCodeEnum;
import com.gl365.app.remote.merchant.MerchantRemoteService;
import com.gl365.app.remote.merchant.request.UpdateMerchantInfoReqDto;
import com.gl365.app.remote.merchant.response.MerchantBasicsInfoDto;
import com.gl365.app.remote.merchant.response.MerchantOperatorDto;
import com.gl365.app.service.MerchantOperatorService;
import com.gl365.app.service.RedisService;
import com.gl365.app.utils.JsonUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * < 商家基础Controller >
 * 
 * @author hui.li 2017年5月4日 - 下午12:05:15
 * @Since 1.0
 */
@CrossOrigin(origins = "*", maxAge = 3600) // 跨域问题，与前端调试时打开
@Api(description = "商户相关接口")
@RestController
@RequestMapping("/businessAPI")
public class MerchantController extends BaseController {
	@Autowired
	private RedisService redisService;
	@Autowired
	private MerchantRemoteService merchantRemoteService;
	@Autowired
	private MerchantOperatorService merchantOperatorService;
	private static final Logger LOG = LoggerFactory.getLogger(MerchantController.class);
	
	@ApiOperation("更新商户基础信息")
	@Permis(permission = PermisConstant.ADMIN + PermisConstant.StoreManager)
	@PostMapping("/merchant/update/info")
	public Object updateMerchantInfo(@RequestBody UpdateMerchantInfoReqDto req, HttpServletRequest request) {
		LOG.info("更新商户基础信息>>>入参：{}", req);
		try {
			Object result = this.merchantOperatorService.updateMerchantInfo(req, getCurrentOperatorId());
			return result;
		} catch (Exception e) {
			LOG.info("更新商户基础信息>>>错误：{}", e);
			return ResultDto.errorResult();
		}
	}

	/**
	 * 查询商户基础信息
	 *
	 * dfs_518 <br/>
	 * 2017年5月19日 下午4:52:45 <br/>
	 * 
	 * @param request
	 * @return
	 *
	 * 		Object
	 */
	@ApiOperation("查询商户基础信息")
	@Permis
	@GetMapping("/merchant/info")
	public Object getMerchantInfo(HttpServletRequest request) {
		String operatorId = getCurrentOperatorId();
		LOG.info("查询商户基础信息>>>入参：{}", operatorId);
		try {
			MerchantOperatorDto operator = (MerchantOperatorDto) redisService.get(operatorId);
			return merchantRemoteService.getDetail(operator.getMerchantNo());
		}
		catch (Exception e) {
			LOG.info("查询商户基础信息>>>错误：{}", e);
			return ResultDto.errorResult();
		}
	}

	/**
	 * 查询商户基础信息
	 *
	 * dfs_518 <br/>
	 * 2017年5月19日 下午4:53:23 <br/>
	 * 
	 * @param request
	 * @return
	 *
	 * 		Object
	 */
	@ApiOperation(value = "查询商户基础信息", httpMethod = "GET", response = ResultDto.class)
	@GetMapping("/merchant/basics")
	@Permis
	public Object getMerchantBasicsInfo(HttpServletRequest request) {
		return getMerchantBasicsInfoHandle(request, null);
	}

	public Object getMerchantBasicsInfoHandle(HttpServletRequest request, String id) {
		String operatorId = null;
		if (StringUtils.isNotBlank(id)) {
			operatorId = id;
		}
		else {
			Object reqId = request.getSession().getAttribute(HttpParamConstant.Session.GL_APP_USER_ID);
			if (reqId != null) {
				operatorId = reqId.toString();
			}
		}
		LOG.info("查询商户基础信息>>>入参：{}", operatorId);
		try {
			ResultDto<MerchantOperatorDto> result = merchantRemoteService.getOperatorByOperatorId(operatorId);
			if (!ResultCodeEnum.System.SUCCESS.getCode().equals(result.getResult())) {
				LOG.info("查询商户基础信息>>>出参：{}", JsonUtils.toJsonString(result));
				return result;
			}
			MerchantOperatorDto operator = result.getData();
			LOG.info("MerchantOperatorDto data={}", JsonUtils.toJsonString(operator));
			if (StringUtils.isBlank(operator.getMerchantNo()) || Integer.valueOf(QuitTypeEnum.LEAVE.getValue()).equals(operator.getQuit())) {
				// 商户编号为空时,认为是游客登录。返回自定义的商户数据
				MerchantBasicsInfoDto info = new MerchantBasicsInfoDto();
				info.setMerchantName("暂无商户");
				info.setMerchantShortName("暂无商户");
				info.setRole(PermisConstant.Leave);
				info.setCashierBoo(false);
				info.setStaffmanagementBoo(false);
				ResultDto<MerchantBasicsInfoDto> rlt = new ResultDto<>(ResultCodeEnum.System.SUCCESS, info);
				LOG.info("查询商户基础信息>>>出参：{}", JsonUtils.toJsonString(rlt));
				return rlt;
			}
			ResultDto<MerchantBasicsInfoDto> rlt = merchantRemoteService.getBasicsDetail(operator.getMerchantNo());
			LOG.info("merchant返回查询商户基础信息结果： rlt={}", JsonUtils.toJsonString(rlt));
			// 组装查询当前商户的用户信息
			if (ResultCodeEnum.System.SUCCESS.getCode().equals(rlt.getResult())) {
				if (null != rlt.getData()) {
					// 处理连锁商户角色问题
					String token = request.getHeader(HttpParamConstant.Headers.GL_TOKEN);
					if (StringUtils.isBlank(token)) {
						LOG.warn("<====NEED_LOGIN====>处理连锁商户角色问题 token失效");
						return ResultDto.result(ResultCodeEnum.System.NEED_LOGIN);
					}
					ResultDto<?> roleRlt = getRoleChainBusiness(token, id);
					if (!ResultCodeEnum.System.SUCCESS.getCode().equals(roleRlt.getResult())) { return roleRlt; }
					String roleData = (String) roleRlt.getData();
					if (StringUtils.isNotBlank(roleData)) {
						rlt.getData().setRole(roleData);
					}
					else {
						rlt.getData().setRole((QuitTypeEnum.LEAVE.getValue() == operator.getQuit()) ? "4" : operator.getRoleId());
					}
					if (OperatorSignEnum.CASHIER_MANAGE_SIGN.getValue().equals(operator.getOperatorSign())) {
						rlt.getData().setCashierBoo(true);
						rlt.getData().setStaffmanagementBoo(true);
					}
					else if (OperatorSignEnum.MANAGE_SIGN.getValue().equals(operator.getOperatorSign())) {
						rlt.getData().setCashierBoo(false);
						rlt.getData().setStaffmanagementBoo(true);
					}
					else if (OperatorSignEnum.CASHIER_SIGN.getValue().equals(operator.getOperatorSign())) {
						rlt.getData().setCashierBoo(true);
						rlt.getData().setStaffmanagementBoo(false);
					}
					else {
						rlt.getData().setCashierBoo(false);
						rlt.getData().setStaffmanagementBoo(false);
					}
				}
				// 管理员不受此权限限制
				if (PermisConstant.ADMIN.equals(operator.getRoleId())) {
					rlt.getData().setCashierBoo(true);
					rlt.getData().setStaffmanagementBoo(true);
					// rlt.getData().setCashierBoo(false);
					// rlt.getData().setStaffmanagementBoo(false);
					// WalletUrlReq walletUrlReq = new WalletUrlReq();
					// walletUrlReq.setPuserNo(operator.getMerchantNo());
					// walletUrlReq.setPono(UUID.randomUUID().toString().replaceAll("-",
					// ""));
					// ResultDto<WalletUrlRsp> resultDto =
					// accountService.openResult(walletUrlReq);
					// if (resultDto != null && resultDto.getData() != null &&
					// "3".equals(resultDto.getData().getStatus())) {
					// rlt.getData().setCashierBoo(true);
					// rlt.getData().setStaffmanagementBoo(true);
					// }
				}
			}
			LOG.info("查询商户基础信息>>>出参：{}", JsonUtils.toJsonString(rlt));
			return rlt;
		}
		catch (Exception e) {
			LOG.info("查询商户基础信息>>>错误：{}", e);
			return ResultDto.errorResult();
		}
	}

	private ResultDto<?> getRoleChainBusiness(String token, String id) {
		String mainOperatorId = (String) redisService.get(UserHandler.CHAIN_BUSINESS_PREFIX + token);
		LOG.info("查询商户基础信息获取连锁店id：{}", mainOperatorId);
		if (StringUtils.isBlank(mainOperatorId)) {
			if (StringUtils.isBlank(id)) { return new ResultDto<>(ResultCodeEnum.System.SUCCESS, null); }
			LOG.warn("<====NEED_LOGIN====>处理连锁商户角色问题获取总店token失效 ");
			return new ResultDto<>(ResultCodeEnum.System.NEED_LOGIN);
		}
		ResultDto<MerchantOperatorDto> mainOperatorDto = merchantRemoteService.getOperatorByOperatorId(mainOperatorId);
		if (!ResultCodeEnum.System.SUCCESS.getCode().equals(mainOperatorDto.getResult())) { return mainOperatorDto; }
		String role = null;
		int linkType = mainOperatorDto.getData().getLinkType();// 连锁结算类型：0非连锁;1统一结算连锁;2单独结算连锁
		String merchantType = mainOperatorDto.getData().getMerchantType();// 1商家总店2商家门店3分销商4品牌店
		if ((linkType == 1 || linkType == 2) && "1".equals(merchantType)) {
			if (linkType == 1) {
				role = "5";
			}
			else {
				role = "6";
			}
		}
		LOG.info("查询商户基础信息  getRoleChainBusiness rlt={}", role);
		return new ResultDto<>(ResultCodeEnum.System.SUCCESS, role);
	}
}
