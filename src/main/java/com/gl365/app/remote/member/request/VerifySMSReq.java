package com.gl365.app.remote.member.request;
import java.io.Serializable;
import com.gl365.app.utils.JsonUtils;
public class VerifySMSReq implements Serializable {
	private static final long serialVersionUID = 1L;
	private String phoneNo;
	private String verifyCode;
	/**
	 * 业务类型
	 * 0：注册
	 * 1：修改手机号
	 * 2：登录
	 * 3：忘记登录密码
	 * 4：设置银行卡
	 * 5: 商家绑定员工
	 */
	private Integer businessType;

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	@Override
	public String toString() {
		return JsonUtils.toJsonString(this);
	}
}
