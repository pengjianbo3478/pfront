package com.gl365.app.dto;
import io.swagger.annotations.ApiModelProperty;
/**
 * < 登录指令 >
 *
 * @author hui.li 2017年4月12日 - 下午1:05:55
 * @Since 1.0
 */
public class LoginCommand extends Command {
	private static final long serialVersionUID = 4098317368451844677L;
	/**
	 * 用户登录名
	 */
	private String username;
	/**
	 * 用户登录密码
	 */
	private String password;
	/**
	 * 设备名称 例Iphone6s 华为P9
	 */
	private String deviceName;
	/**
	 * 操作系统及版本号 例ios 10.3 android 6.0
	 */
	private String deviceVersion;
	// 接口版本
	@ApiModelProperty("接口版本：0.0.1")
	private String apiVersion;

	@ApiModelProperty("登录名,即手机号")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@ApiModelProperty("密码")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ApiModelProperty("设备名称 例Iphone6s 华为P9")
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	@ApiModelProperty("操作系统及版本号 例ios 10.3 android 6.0")
	public String getDeviceVersion() {
		return deviceVersion;
	}

	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}

	@ApiModelProperty(hidden = true)
	@Override
	public String getToken() {
		return super.getToken();
	}

	@ApiModelProperty(hidden = true)
	@Override
	public String getDeviceId() {
		return super.getDeviceId();
	}

	@ApiModelProperty(hidden = true)
	@Override
	public String getClientId() {
		return super.getClientId();
	}

	@ApiModelProperty(hidden = true)
	@Override
	public String getClientVer() {
		return super.getClientVer();
	}

	@ApiModelProperty(hidden = true)
	@Override
	public String getTimestamp() {
		return super.getTimestamp();
	}

	@ApiModelProperty(hidden = true)
	@Override
	public String getSign() {
		return super.getSign();
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
}
