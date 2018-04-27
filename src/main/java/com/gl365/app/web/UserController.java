package com.gl365.app.web;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
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
import com.gl365.app.dto.Command;
import com.gl365.app.dto.LoginCommand;
import com.gl365.app.dto.ResultDto;
import com.gl365.app.enums.ResultCodeEnum;
import com.gl365.app.enums.SMSTypeEnum;
import com.gl365.app.remote.member.MemberRemoteService;
import com.gl365.app.remote.member.request.SendSMSReq;
import com.gl365.app.remote.member.request.UserDto;
import com.gl365.app.remote.member.request.UserForgotPwdDto;
import com.gl365.app.remote.member.request.UserForgotPwdSmsDto;
import com.gl365.app.remote.member.request.UserUpdateDto;
import com.gl365.app.remote.member.request.VerifySMSReq;
import com.gl365.app.remote.merchant.MerchantRemoteService;
import com.gl365.app.remote.merchant.response.MerchantOperatorDto;
import com.gl365.app.service.RedisService;
import com.gl365.app.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * < 用户行为Controller >
 * 
 */
@CrossOrigin(origins = "*", maxAge = 3600) // 跨域问题，与前端调试时打开
@Api(description = "用户登录注销注册相关接口")
@RestController
@RequestMapping("/businessAPI")
public class UserController {
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	public static final String FORGOT_PWD_ADMIN = "1";// 忘记密码管理员标识
	@Autowired
	private UserHandler userHandler;
	@Autowired
	private RedisService redisService;
	@Autowired
	private MemberRemoteService memberRemoteService;// 调member
	@Autowired
	private MerchantRemoteService merchantRemoteService;

	private String isLogin(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Object userId = session.getAttribute(HttpParamConstant.Session.GL_APP_USER_ID);
			return userId == null ? null : (String) userId;
		}
		return null;
	}

	@ApiOperation("登录")
	@PostMapping("/login")
	public Object login(HttpServletRequest sRequest, @RequestBody LoginCommand command) {
		Object rlt = null;
		Long begin = System.currentTimeMillis();
		try {
			LOG.info("pfront user login begin,param={}", JsonUtils.toJsonString(command));
			HttpServletRequest request = (HttpServletRequest) sRequest;
			if (StringUtils.isBlank(command.getUsername())) { return new ResultDto<>(ResultCodeEnum.System.PARAM_NULL); }
			rlt = userHandler.login(command, request);
		}
		catch (Exception e) {
			LOG.error("pfront user login exception：{}", e);
			rlt = ResultDto.errorResult();
		}
		Long end = System.currentTimeMillis();
		LOG.info("pfront user login end  rlt={}  time={} ms", JsonUtils.toJsonString(rlt), end - begin);
		return rlt;
	}

	@ApiOperation("注销")
	@PostMapping("/logout")
	public ResultDto<?> logout(HttpServletRequest request) {
		ResultDto<?> rlt = null;
		Long begin = System.currentTimeMillis();
		try {
			String mainOperatorId = null;
			LOG.info("pfront user logout begin param={}", mainOperatorId);
			String userId = isLogin(request);
			if (StringUtils.isBlank(userId)) {
				LOG.warn("<====NEED_LOGIN====>用户注销 ");
				return ResultDto.result(ResultCodeEnum.System.NEED_LOGIN);
			}
			Command command = new Command();
			command.setToken(request.getHeader(HttpParamConstant.Headers.GL_TOKEN));
			if (StringUtils.isBlank(command.getToken())) { return ResultDto.result(ResultCodeEnum.System.PARAM_NULL); }
			rlt = userHandler.logout(request, command, mainOperatorId);
		}
		catch (Exception e) {
			LOG.error("pfront user logout exception：{}", e);
			rlt = ResultDto.errorResult();
		}
		Long end = System.currentTimeMillis();
		LOG.info("pfront user logout end  rlt={}  time={} ms", JsonUtils.toJsonString(rlt), end - begin);
		return rlt;
	}

	@ApiOperation("判断是否在线")
	@GetMapping("/isLogin")
	public Object userId(HttpServletRequest request) {
		try {
			LOG.info("判是否在线 > > > 入参：{}", "");
			String userId = isLogin(request);
			if (StringUtils.isNotBlank(userId)) {
				return ResultDto.result(ResultCodeEnum.System.SUCCESS);
			}
			else {
				return ResultDto.result(ResultCodeEnum.System.FORCED_LOGIN);
			}
		}
		catch (Exception e) {
			LOG.error("判是否在线  > > > 异常：{}", e);
			return ResultDto.getErrInfo();
		}
	}

	@ApiOperation("修改密码")
	@PostMapping("/updatePassword")
	@Permis
	public Object updatePassWord(HttpServletRequest request, @RequestBody UserDto userDto) {
		try {
			LOG.info("修改密码 > > > 入参：{}", JsonUtils.toJsonString(userDto));
			if (StringUtils.isBlank(userDto.getOldPassword()) || StringUtils.isBlank(userDto.getNewPassword())) { return ResultDto.result(ResultCodeEnum.System.PARAM_NULL); }
			String userId = isLogin(request);
			if (!StringUtils.isBlank(userId)) {
				userDto.setUserId(userId);
				return userHandler.updatePassWord(userDto);
			}
			else {
				LOG.warn("<====NEED_LOGIN====>修改密码 ");
				return ResultDto.result(ResultCodeEnum.System.NEED_LOGIN);
			}
		}
		catch (Exception e) {
			LOG.error("商家修改密码  > > > 异常：{}", e);
			return ResultDto.getErrInfo();
		}
	}

	@ApiOperation("忘记密码发送短信,0代表普通用户,1代表管理员")
	@PostMapping("/user/forgotPwdSendSmsCode")
	public Object forgotPwdSendSmsCode(@RequestBody UserForgotPwdSmsDto req) {
		if (req == null) {
			req = new UserForgotPwdSmsDto();
		}
		String mobile = req.getMobilePhone();
		String isAdmin = req.getIsAdmin();
		LOG.info("忘记密码发送短信 forgotPwdSendSmsCode begin  mobile={},isAdmin={}", mobile, isAdmin);
		Long beginTime = System.currentTimeMillis();
		ResultDto<?> rlt = null;
		try {
			if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(isAdmin)) { return ResultDto.result(ResultCodeEnum.System.PARAM_NULL); }
			SendSMSReq sendSMSReq = new SendSMSReq();
			if (isAdmin.equals(FORGOT_PWD_ADMIN)) {
				sendSMSReq.setBusinessType(SMSTypeEnum.UPDATE_PASSWORD_ADMIN.getCode());
			}
			else {
				sendSMSReq.setBusinessType(SMSTypeEnum.UPDATE_PASSWORD.getCode());
			}
			sendSMSReq.setPhoneNo(mobile);
			rlt = memberRemoteService.sendSms(sendSMSReq);
		}
		catch (Exception e) {
			LOG.error("忘记密码发送短信 forgotPwdSendSmsCode  ===> smsService.verify exception,e：" + e);
			rlt = ResultDto.errorResult();
		}
		Long endTime = System.currentTimeMillis();
		LOG.info("忘记密码发送短信 forgotPwdSendSmsCode end  rlt={},time={}ms", JsonUtils.toJsonString(rlt), (endTime - beginTime));
		return rlt;
	}

	@ApiOperation("忘记密码校验短信")
	@PostMapping("/user/forgotPwdVerifySmsCode")
	public Object forgotPwdverifySmsCode(@RequestBody UserForgotPwdSmsDto req) {
		LOG.info("忘记密码校验短信 forgotPwdverifySmsCode begin  param={}", JsonUtils.toJsonString(req));
		Long beginTime = System.currentTimeMillis();
		ResultDto<?> rlt = null;
		try {
			if (StringUtils.isEmpty(req.getMobilePhone()) || StringUtils.isEmpty(req.getIsAdmin()) || StringUtils.isEmpty(req.getVerifyCode())) { return ResultDto.result(ResultCodeEnum.System.PARAM_NULL); }
			VerifySMSReq verifySMSReq = new VerifySMSReq();
			if (FORGOT_PWD_ADMIN.equals(req.getIsAdmin())) {
				verifySMSReq.setBusinessType(SMSTypeEnum.UPDATE_PASSWORD_ADMIN.getCode());
			}
			else {
				verifySMSReq.setBusinessType(SMSTypeEnum.UPDATE_PASSWORD.getCode());
			}
			verifySMSReq.setPhoneNo(req.getMobilePhone());
			verifySMSReq.setVerifyCode(req.getVerifyCode());
			ResultDto<?> resp = memberRemoteService.verifySmsCode(verifySMSReq);
			rlt = resp;
			// 忘记密码返回密钥,防止系统被刷
			if (ResultCodeEnum.System.SUCCESS.getCode().equals(resp.getResult())) {
				String token = UUID.randomUUID().toString();
				StringBuffer sb = new StringBuffer(UserHandler.FORGOT_PWD_PREFIX);
				redisService.set(sb.append(token).toString(), token, 600L);
				Map<String, Object> rltMap = new HashMap<>();
				rltMap.put("token", token);
				rlt = new ResultDto<>(ResultCodeEnum.System.SUCCESS, rltMap);
			}
		}
		catch (Exception e) {
			LOG.error("忘记密码校验短信 forgotPwdverifySmsCode  ===> smsService.verify exception,e：" + e);
			rlt = ResultDto.errorResult();
		}
		Long endTime = System.currentTimeMillis();
		LOG.info("忘记密码校验短信 forgotPwdverifySmsCode end  rlt={},time={}ms", JsonUtils.toJsonString(rlt), (endTime - beginTime));
		return rlt;
	}

	@ApiOperation("忘记密码")
	@PostMapping("/user/forgotPassword")
	public Object forgotPassword(@RequestBody UserForgotPwdDto userForgotPwdDto) {
		LOG.info("忘记密码 forgotPassword begin  param={}", JsonUtils.toJsonString(userForgotPwdDto));
		Long beginTime = System.currentTimeMillis();
		ResultDto<?> rlt = null;
		try {
			if (StringUtils.isBlank(userForgotPwdDto.getMobilePhone()) || StringUtils.isBlank(userForgotPwdDto.getToken()) || StringUtils.isBlank(userForgotPwdDto.getIsAdmin())) { return ResultDto.result(ResultCodeEnum.System.PARAM_NULL); }
			rlt = userHandler.forgotPassword(userForgotPwdDto);
		}
		catch (Exception e) {
			LOG.error("忘记密码 forgotPassword invoking member project exception  ===> userService.forgotPassword exception,e:{}", e);
			rlt = ResultDto.result(ResultCodeEnum.System.SYSTEM_ERROR);
		}
		Long endTime = System.currentTimeMillis();
		LOG.info("忘记密码 forgotPassword end  rlt={},time={}ms", JsonUtils.toJsonString(rlt), (endTime - beginTime));
		return rlt;
	}

	@ApiOperation("根据管理员手机号获取相关的账号")
	@PostMapping("/user/getAdminByMobile")
	public Object getAdminByMobile(@RequestBody UserForgotPwdDto userForgotPwdDto) {
		LOG.info("getAdminByMobile begin param={}", JsonUtils.toJsonString(userForgotPwdDto));
		Long beginTime = System.currentTimeMillis();
		ResultDto<?> rlt = null;
		try {
			if (StringUtils.isBlank(userForgotPwdDto.getMobilePhone()) || StringUtils.isBlank(userForgotPwdDto.getToken())) { return ResultDto.result(ResultCodeEnum.System.PARAM_NULL); }
			rlt = userHandler.getAdminByMobile(userForgotPwdDto);
		}
		catch (Exception e) {
			LOG.error("userService.getAdminByMobile exception,e:{}", e);
			rlt = new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR);
		}
		Long endTime = System.currentTimeMillis();
		LOG.info("getAdminByMobile end  rlt={},time={}ms", JsonUtils.toJsonString(rlt), (endTime - beginTime));
		return rlt;
	}

	@ApiOperation("修改密码校验原密码")
	@PostMapping("/user/updatePwd")
	@Permis
	public Object updatePwd(HttpServletRequest request, @RequestBody UserForgotPwdDto userForgotPwdDto) {
		try {
			String pwd = userForgotPwdDto.getPassword();
			LOG.info("修改密码校验原密码 > > > 入参：{}", pwd);
			if (StringUtils.isBlank(pwd)) { return ResultDto.result(ResultCodeEnum.System.PARAM_NULL); }
			String operaterId = isLogin(request);
			MerchantOperatorDto operater = (MerchantOperatorDto) redisService.get(operaterId);
			if ((!PermisConstant.ADMIN.equals(operater.getRoleId())) && StringUtils.isBlank(operater.getUserId())) { return ResultDto.result(ResultCodeEnum.System.SYSTEM_DATA_EXECEPTION); }
			if (StringUtils.isNotBlank(operaterId)) {
				ResultDto<?> rlt = new ResultDto<>(ResultCodeEnum.User.PWD_MATCHING_ERROR);
				if (PermisConstant.ADMIN.equals(operater.getRoleId())) {
					// 管理员校验密码
					ResultDto<MerchantOperatorDto> merchantOperatorDto = merchantRemoteService.getOperatorByOperatorId(operaterId);
					if (merchantOperatorDto != null && merchantOperatorDto.getData() != null && pwd.equals(merchantOperatorDto.getData().getPassword())) {
						rlt = new ResultDto<>(ResultCodeEnum.System.SUCCESS);
					}
				}
				else {
					// 非管理员校验密码
					UserDto userDto = new UserDto();
					userDto.setUserId(operater.getUserId());
					UserUpdateDto user = memberRemoteService.getUserInfoByUserId(userDto);
					if (user != null && pwd.equals(user.getPassword())) {
						rlt = new ResultDto<>(ResultCodeEnum.System.SUCCESS);
					}
				}
				return rlt;
			}
			else {
				LOG.warn("<====NEED_LOGIN====>修改密码校验原密码");
				return ResultDto.result(ResultCodeEnum.System.NEED_LOGIN);
			}
		}
		catch (Exception e) {
			LOG.error("修改密码校验原密码   > > > 异常：{}", e);
			return ResultDto.errorResult();
		}
	}
}
