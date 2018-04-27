package com.gl365.app.remote.merchant.request;

public class MerchantSaveOperatorReqDto extends MerchantOperatorReqDto {

	private String merchantNo; //商家编号

	
	
	public MerchantSaveOperatorReqDto(MerchantOperatorReqDto command, String merchantNo) {
		super(command);
		this.merchantNo = merchantNo;
	}
	
	public MerchantSaveOperatorReqDto() {
		
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	
}
