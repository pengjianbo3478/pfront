package com.gl365.app.remote.member.request;
import java.io.Serializable;
import java.util.List;
public class UserDto implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 用户登录修改密码
	 */
	private String operatorId;
	private String userId;
	private String oldPassword;
	private String newPassword;
	/**
	 * 注册相关
	 */
	private String username;
	private String mobilePhone;
	private String password;
	private String regToken;
	private List<String> operatorIdList;
	private List<String> userIdList;
	/**
	 * url头像
	 */
	private String url;

	public UserDto() {
	}

	public UserDto(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegToken() {
		return regToken;
	}

	public void setRegToken(String regToken) {
		this.regToken = regToken;
	}

	public List<String> getOperatorIdList() {
		return operatorIdList;
	}

	public void setOperatorIdList(List<String> operatorIdList) {
		this.operatorIdList = operatorIdList;
	}

	public List<String> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
