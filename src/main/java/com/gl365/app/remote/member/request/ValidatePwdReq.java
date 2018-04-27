package com.gl365.app.remote.member.request;
import java.io.Serializable;
import com.gl365.app.utils.JsonUtils;
public class ValidatePwdReq implements Serializable {
	private static final long serialVersionUID = -7523796439066959275L;
	private String userId;
	private String pwd;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return JsonUtils.toJsonString(this);
	}
}
