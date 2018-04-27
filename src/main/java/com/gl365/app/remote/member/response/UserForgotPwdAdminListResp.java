package com.gl365.app.remote.member.response;
public class UserForgotPwdAdminListResp {
	/**
	 * 绑定的手机号
	 */
	private String mobilePhone;
	/**
	 * 管理员账号
	 */
	private String operatorId;
	/**
	 * 对外经营名称(商家简称)
	 */
	private String shortName;

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
}
