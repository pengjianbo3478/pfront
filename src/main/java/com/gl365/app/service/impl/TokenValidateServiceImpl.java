package com.gl365.app.service.impl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gl365.app.common.UserHandler;
import com.gl365.app.security.TokenValidateService;
import com.gl365.app.service.RedisService;
@Component
public class TokenValidateServiceImpl implements TokenValidateService {
	private static final Logger LOG = LoggerFactory.getLogger(TokenValidateServiceImpl.class);
	@Autowired
	private RedisService redisService;

	@Override
	public boolean validate(String token, String deviceId, String clientId, String clientVer, String timestamp, String reqSign) {
		LOG.debug("GL_TOKEN {} GL_REQ_SIGN {}", token, reqSign);
		boolean isValidSign = false;
		try {
			if (StringUtils.isNotBlank(reqSign)) {
				String key = UserHandler.GL_PC_LOGIN_SALT + token;
				Object value = redisService.get(key);
				LOG.info("validate.value{}", value);
				String sign = value == null ? null : (String) value;
				if (StringUtils.isNotBlank(sign) && sign.equals(reqSign)) {
					isValidSign = true;
					redisService.set(key, sign, 7200L);
				}
			}
		}
		catch (Exception e) {
			LOG.error("签名验证异常导致失败", e);
			isValidSign = false;
		}
		return isValidSign;
	}
}
