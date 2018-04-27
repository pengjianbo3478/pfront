package com.gl365.app.common;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import com.gl365.app.dto.Command;
/**
 * < VOBO转换、VO头信息转换 >
 * 
 * @author hui.li 2017年4月13日 - 上午10:16:18
 * @Since 1.0
 */
@Component("userBeanConvert")
public class UserBeanConvert {
	/**
	 * 头信息转基础指令
	 */
	public static void Headers2Command(Command command, HttpServletRequest request) {
		command.setToken(request.getHeader(HttpParamConstant.Headers.GL_TOKEN));
		command.setDeviceId(request.getHeader(HttpParamConstant.Headers.GL_DEVICE_ID));
		command.setClientId(request.getHeader(HttpParamConstant.Headers.GL_CLIENT_ID));
		command.setClientVer(request.getHeader(HttpParamConstant.Headers.GL_CLIENT_VER));
		command.setTimestamp(request.getHeader(HttpParamConstant.Headers.GL_TIMESTAMP));
		command.setSign(request.getHeader(HttpParamConstant.Headers.GL_REQ_SIGN));
	}
}
