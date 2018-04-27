package com.gl365.app.common;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gl365.app.dto.Command;
import com.gl365.app.dto.LoginCommand;
import com.gl365.app.dto.ResultDto;
import com.gl365.app.enums.QuitTypeEnum;
import com.gl365.app.enums.ResultCodeEnum;
import com.gl365.app.remote.member.MemberRemoteService;
import com.gl365.app.remote.member.request.UserDto;
import com.gl365.app.remote.member.request.UserForgotPwdDto;
import com.gl365.app.remote.member.request.UserLoginDto;
import com.gl365.app.remote.member.request.UserOperatorDto;
import com.gl365.app.remote.member.request.UserUpdateDto;
import com.gl365.app.remote.merchant.MerchantRemoteService;
import com.gl365.app.remote.merchant.response.MerchantBasicsInfoDto;
import com.gl365.app.remote.merchant.response.MerchantOperatorDto;
import com.gl365.app.security.PersistTokenService;
import com.gl365.app.service.RedisService;
import com.gl365.app.utils.JsonUtils;
import com.gl365.app.utils.MD5Utils;
import com.gl365.app.web.UserController;
@Component("userManager")
public class UserHandler {
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	public static final String CHAIN_BUSINESS_PREFIX = "CHAIN_BUSINESS_PREFIX";// 连锁商户token
																				// key前缀
	public static final String FORGOT_PWD_PREFIX = "FORGOT_PWD_PREFIX";// 忘记密码token
																		// key前缀
	public static final String GL_PC_LOGIN_SALT = "GL_PC_LOGIN_SALT";// pc登录的盐
	@Autowired
	private RedisService redisService;
	@Autowired
	private PersistTokenService tokenService;
	@Autowired
	private MemberRemoteService memberRemoteService;// 调member
	@Autowired
	private MerchantRemoteService merchantService;// 调merchant

	public Object login(LoginCommand command, HttpServletRequest request) throws Exception {
		// 1.获取用户信息
		String username = command.getUsername();
		Boolean isChinaPhoneLegal = isChinaPhoneLegal(username);
		UserUpdateDto merchantUser = null;
		if (isChinaPhoneLegal) {
			merchantUser = getMerchantUser(username);
			if (null == merchantUser) { return new ResultDto<>(ResultCodeEnum.User.NO_USER_INFO_ERROR); }
			if (1 != merchantUser.getStatus()) {
				if (0 == merchantUser.getStatus()) {
					ResultDto<MerchantOperatorDto> result = new ResultDto<>(ResultCodeEnum.User.USER_STATUS_ERROR);
					result.setDescription("用户未激活,请先在会员端登录");
					return result;
				}
				return new ResultDto<>(ResultCodeEnum.User.USER_STATUS_ERROR);
			}
		}
		// 2.获取B端用户信息
		UserLoginDto loginDto = new UserLoginDto();
		loginDto.setCommand(command);
		loginDto.setMerchantUser(merchantUser);
		ResultDto<MerchantOperatorDto> userRt = merchantService.login(loginDto);
		if (!ResultCodeEnum.System.SUCCESS.getCode().equals(userRt.getResult())) { return userRt; }
		MerchantOperatorDto operator = userRt.getData();
		// bfront PC班只有管理员和店长有权限登录
		String role = operator.getRoleId();
		if (role == null || ((!PermisConstant.ADMIN.equals(role))) && (!PermisConstant.StoreManager.equals(role))) { return new ResultDto<>(ResultCodeEnum.User.OPERATOR_NOT_LOGIN); }
		String name = operator.getOperatorName();
		String photo = operator.getImage();
		operator.setImage(null);
		if (isChinaPhoneLegal) {
			name = merchantUser.getRealName();
			photo = merchantUser.getPhoto();
		}
		String userId = operator.getUserId();
		String operatorId = operator.getOperatorId();
		// 3.保存Token
		String token = UUID.randomUUID().toString();
		Long liveTime = 7200L;
		tokenService.updateToken(token, operatorId, liveTime);
		// 4.构建出参
		Map<String, Object> rtMap = new HashMap<>();
		rtMap.put("token", token);
		rtMap.put("role", (Integer.valueOf(QuitTypeEnum.LEAVE.getValue()).equals(operator.getQuit())) ? "4" : operator.getRoleId());
		String merchantNo = (Integer.valueOf(QuitTypeEnum.LEAVE.getValue()).equals(operator.getQuit())) ? "" : operator.getMerchantNo();
		rtMap.put("merchantNo", merchantNo);
		rtMap.put("merchantShortName", getMerchantShortName(merchantNo));
		rtMap.put("parentMerchantNo", operator.getParentMerchantNo());
		rtMap.put("linkType", operator.getLinkType());
		rtMap.put("merchantType", operator.getMerchantType());
		rtMap.put("operatorId", operatorId);
		rtMap.put("userId", userId);
		rtMap.put("name", name);
		rtMap.put("photo", photo);
		boolean isTourist = false;// 为true时确定是游客登录,此时缓存中没有商户号
		if (null == operator.getMerchantNo()) {
			isTourist = true;
		}
		rtMap.put("isTourist", isTourist);
		rtMap = buildChainBusinessLoginRole(rtMap, operator, token, operatorId);
		request.getSession(true).setAttribute(HttpParamConstant.Session.GL_APP_USER_ID, operatorId);
		// 签名用的时间戳
		String timestamp = String.valueOf(System.currentTimeMillis());
		String value = MD5Utils.md5Hex(GL_PC_LOGIN_SALT + timestamp);
		rtMap.put("timestamp", timestamp);
		redisService.set(GL_PC_LOGIN_SALT + token, value, liveTime);
		// 缓存
		liveTime = 86400L;
		redisService.set(operatorId, operator, liveTime);
		return new ResultDto<>(ResultCodeEnum.System.SUCCESS, rtMap);
	}

	private UserUpdateDto getMerchantUser(String username) {
		UserUpdateDto userUpdateDto = new UserUpdateDto();
		userUpdateDto.setMobilePhone(username);
		UserUpdateDto result = memberRemoteService.getUserInfoByMobilePhone(userUpdateDto);
		return result;
	}

	private String getMerchantShortName(String merchantNo) {
		String merchantShortName = "";
		if (StringUtils.isNotBlank(merchantNo)) {
			ResultDto<MerchantBasicsInfoDto> rlt = merchantService.getBasicsDetail(merchantNo);
			if (rlt != null && rlt.getData() != null && rlt.getData().getMerchantShortName() != null) {
				merchantShortName = rlt.getData().getMerchantShortName();
			}
		}
		return merchantShortName;
	}

	// 判断统一结算非统一结算角色类型
	private Map<String, Object> buildChainBusinessLoginRole(Map<String, Object> rtMap, MerchantOperatorDto operator, String token, String operatorId) {
		if (Integer.valueOf(QuitTypeEnum.ONJOB.getValue()).equals(operator.getQuit())) {
			int linkType = operator.getLinkType();// 连锁结算类型：0非连锁;1统一结算连锁;2单独结算连锁
			String merchantType = operator.getMerchantType();// 1商家总店2商家门店3分销商4品牌店
			if ((linkType == 1 || linkType == 2) && "1".equals(merchantType)) {
				// 该redis用于连锁总公司保存id,用于查询basic接口处理返回角色问题
				redisService.set(CHAIN_BUSINESS_PREFIX + token, operatorId, 2592000L);
				if (linkType == 1) {
					rtMap.put("role", "5");
				}
				else {
					rtMap.put("role", "6");
				}
			}
		}
		return rtMap;
	}

	public boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
		String regExp = "^1(3|4|5|7|8)\\d{9}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	public ResultDto<?> logout(HttpServletRequest request, Command command, String mainOperatorId) {
		LOG.info("用户注销,参数：[{}]", ToStringBuilder.reflectionToString(command));
		try {
			if (StringUtils.isBlank(getPersistToken(command.getToken()))) {
				// 2.Token验证不通过
				LOG.warn("<====NEED_LOGIN====>用户注销,Token验证不通过param={}", JsonUtils.toJsonString(command));
				return new ResultDto<>(ResultCodeEnum.System.NEED_LOGIN);
			}
			else {
				// 3.更新Token
				HttpSession session = request.getSession(false);
				if (null != session) {
					session.invalidate();
				}
				tokenService.clearToken(command.getToken());
				return ResultDto.result(ResultCodeEnum.System.SUCCESS);
			}
		}
		catch (Exception e) {
			LOG.error("注销失败,错误：{}", e);
			return ResultDto.result(ResultCodeEnum.System.SYSTEM_ERROR);
		}
	}

	private String getPersistToken(String token) {
		String rlt = tokenService.getPersistToken(token);
		if (StringUtils.isBlank(rlt)) {
			LOG.warn("用户注销,获取token为空pram={},rlt={}", token, rlt);
		}
		return rlt;
	}

	// 修改密码
	public Object updatePassWord(UserDto userDto) {
		if (userDto.getOldPassword().equals(userDto.getNewPassword())) { return new ResultDto<>(ResultCodeEnum.User.PWD_SAME_ERROR); }
		MerchantOperatorDto operater = (MerchantOperatorDto) redisService.get(userDto.getUserId());
		String role = operater.getRoleId();// 除了管理员其他都是C端用户 管理员(1)
		if (StringUtils.isNotBlank(role) && (PermisConstant.ADMIN.equals(role))) {
			if (userDto.getOldPassword().equals(operater.getPassword())) {
				UserOperatorDto userUpdateDto = new UserOperatorDto();
				userUpdateDto.setOperatorId(operater.getOperatorId());
				userUpdateDto.setPassword(userDto.getNewPassword());
				ResultDto<?> result = merchantService.updateById(userUpdateDto);
				if (!ResultCodeEnum.System.SUCCESS.getCode().equals(result.getResult())) { return result; }
				redisService.del(operater.getOperatorId());
				return result;
			}
		}
		else {
			userDto.setUserId(operater.getUserId());
			UserUpdateDto user = memberRemoteService.getUserInfoByUserId(userDto);
			if (user == null) { return new ResultDto<>(ResultCodeEnum.User.NO_MERCHANT_INFO_ERROR); }
			if (userDto.getOldPassword().equals(user.getPassword())) {
				UserUpdateDto userUpdateDto = new UserUpdateDto();
				userUpdateDto.setUserId(user.getUserId());
				userUpdateDto.setPassword(userDto.getNewPassword());
				return memberRemoteService.updateUserByUserId(userUpdateDto);
			}
		}
		return new ResultDto<>(ResultCodeEnum.User.PWD_MATCHING_ERROR);
	}

	public ResultDto<?> forgotPassword(UserForgotPwdDto userForgotPwdDto) {
		String token = userForgotPwdDto.getToken();
		String key = FORGOT_PWD_PREFIX + userForgotPwdDto.getToken();
		String value = (String) redisService.get(key);
		if (!token.equals(value)) { return new ResultDto<>(ResultCodeEnum.System.KEY_ERROR_EXECEPTION); }
		if (StringUtils.isNotBlank(userForgotPwdDto.getPassword()) && StringUtils.isBlank(userForgotPwdDto.getIdCard())) {
			redisService.del(key);
		}
		// 普通用户忘记密码，需要实名认证(需要身份证)
		if (!UserController.FORGOT_PWD_ADMIN.equals(userForgotPwdDto.getIsAdmin())) {
			ResultDto<?> rlt = memberRemoteService.forgotPassword(userForgotPwdDto);
			if (ResultCodeEnum.System.SUCCESS.getCode().equals(rlt.getResult())) {
				// 忘记密码解除密码输错的锁定和状态
				merchantService.forgotPwdUnLock(userForgotPwdDto.getMobilePhone());
			}
			return rlt;
		}
		// 管理员忘记密码，不需要实名认证(不需要身份证)
		return merchantService.forgotPassword(userForgotPwdDto);
	}

	public ResultDto<?> getAdminByMobile(UserForgotPwdDto userForgotPwdDto) {
		String token = userForgotPwdDto.getToken();
		String key = FORGOT_PWD_PREFIX + userForgotPwdDto.getToken();
		String value = (String) redisService.get(key);
		if (!token.equals(value)) { return new ResultDto<>(ResultCodeEnum.System.KEY_ERROR_EXECEPTION); }
		return merchantService.getAdminByMobile(userForgotPwdDto.getMobilePhone());
	}
}
