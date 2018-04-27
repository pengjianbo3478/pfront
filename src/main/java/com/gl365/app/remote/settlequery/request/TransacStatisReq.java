package com.gl365.app.remote.settlequery.request;
import io.swagger.annotations.ApiModelProperty;
public class TransacStatisReq extends PageReq {
	@ApiModelProperty(value = "商户号", hidden = true)
	private String merchantNo;

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
}
