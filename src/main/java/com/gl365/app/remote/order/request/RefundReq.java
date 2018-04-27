package com.gl365.app.remote.order.request;
import com.gl365.app.dto.BaseDomain;
import com.gl365.app.utils.JsonUtils;
public class RefundReq extends BaseDomain {
	private static final long serialVersionUID = -6631221814112857930L;
	private String orderId;
	private String password;
	private String payId;
	private String apiVersion;

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	@Override
	public String toString() {
		return JsonUtils.toJsonString(this);
	}
}
