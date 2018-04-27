package com.gl365.app.common;
import com.gl365.app.dto.Command;
import com.gl365.app.dto.LoginCommand;
/**
 * < 根据签名算法,对象属性转明文 >
 * 
 * @author hui.li 2017年4月13日 - 下午12:14:53
 * @Since 1.0
 */
public class SignParamConvert {
	/**
	 * 普通接口签名算法
	 * 
	 * sign = (Token+DeviceId+ClientId+ClientVer+TimeStamp) * RSA
	 * 
	 * @param command
	 * @return
	 */
	public static String signConvert4Normal(Command command) {
		StringBuilder sb = new StringBuilder();
		sb.append(command.getToken());
		sb.append(command.getDeviceId());
		sb.append(command.getClientId());
		sb.append(command.getClientVer());
		sb.append(command.getTimestamp());
		return sb.toString();
	}

	/**
	 * 登录接口签名算法
	 * 
	 * sign = (DeviceId+ClientId+ClientVer+TimeStamp+userName+password) * RSA
	 * 
	 * @param command
	 * @return
	 */
	public static String signConvert4Login(LoginCommand command) {
		StringBuilder sb = new StringBuilder();
		sb.append(command.getDeviceId());
		sb.append(command.getClientId());
		sb.append(command.getClientVer());
		sb.append(command.getTimestamp());
		sb.append(command.getUsername());
		sb.append(command.getPassword());
		return sb.toString();
	}
}
