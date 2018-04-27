package com.gl365.app.remote.merchant.request;

public class UploadPicDto {
	
	/**
	 * 商户号
	 */
	private String merchantNo;
	
	/**
	 * 操作员编号
	 */
	private String operatorId;
	
	/**
	 * 操作员名称
	 */
	private String operatorName;
	
	/**
	 * 环境图片地址
	 */
	private String[] picUrl;

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String[] getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String[] picUrl) {
		this.picUrl = picUrl;
	}

	@Override
	public String toString() {
		return "UploadPicDto [merchantNo=" + merchantNo + ", operatorId=" + operatorId + ", operatorName="
				+ operatorName + ", picUrl=" + picUrl + "]";
	}

}
