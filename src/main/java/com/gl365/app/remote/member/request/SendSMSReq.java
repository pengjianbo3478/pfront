package com.gl365.app.remote.member.request;
import java.io.Serializable;
import com.gl365.app.utils.JsonUtils;
public class SendSMSReq implements Serializable {
	private static final long serialVersionUID = 1L;
	private String phoneNo;
	/**
	 * 业务类型
	 * 0：注册
	 * 1：修改手机号
	 * 2：登录
	 * 3：忘记登录密码
	 * 4：设置银行卡
	 * 5：绑定商家员工
	 */
	private Integer businessType;
	/**
	 * 预留字段，后面分语音短信和普通短信
	 */
	private String sendType;

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	@Override
	public String toString() {
		return JsonUtils.toJsonString(this);
	}
}
