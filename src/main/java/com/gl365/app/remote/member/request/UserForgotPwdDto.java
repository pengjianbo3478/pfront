package com.gl365.app.remote.member.request;
public class UserForgotPwdDto {
	private String isAdmin;
	/**
	 * 绑定的手机号
	 */
	private String mobilePhone;
	/**
	 * 绑定的手机号所修改的operatorId
	 */
	private String operatorId;
	/**
	 * 身份证号
	 */
	private String idCard;
	/**
	 * 密钥
	 */
	private String token;
	/**
	 * 密码
	 */
	private String password;

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
