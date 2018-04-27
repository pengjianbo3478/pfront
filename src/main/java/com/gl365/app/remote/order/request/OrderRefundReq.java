package com.gl365.app.remote.order.request;
import java.io.Serializable;
import com.gl365.app.utils.JsonUtils;
public class OrderRefundReq implements Serializable {
	private static final long serialVersionUID = 2826856765172640741L;
	private String origOrderSn;
	/**
	 * 操作人id
	 */
	private String operatorId;

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOrigOrderSn() {
		return origOrderSn;
	}

	public void setOrigOrderSn(String origOrderSn) {
		this.origOrderSn = origOrderSn;
	}

	@Override
	public String toString() {
		return JsonUtils.toJsonString(this);
	}
}
