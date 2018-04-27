package com.gl365.app.remote.merchant.request;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * < 更新商户基本信息指令 >
 * 
 * @author hui.li 2017年5月4日 - 下午4:17:56
 * @Since 1.0
 */
public class UpdateMerchantInfoReqDto {
	/**
	 * 操作员ID
	 */
	private String operatorId;
	
	/**
	 * 会员号
	 */
	private String userId;
	
	/**
	 * 商户号
	 */
	private String merchantNo;

	private String merchantShortName;// 商户简称

	private String offficeContact;// 办公联系人

	private String officeTel;// 办公联系电话

	private String businessTime; // 营业时间

	private String businessAddress;// 商家地址

	private String landmark;// 商家地标物

	private String merchantDesc;// 商家描述

	private String[] specialService;// 商家配置
	
	private BigDecimal avgPrice;//人均消费

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getOffficeContact() {
		return offficeContact;
	}

	public void setOffficeContact(String offficeContact) {
		this.offficeContact = offficeContact;
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public String getBusinessTime() {
		return businessTime;
	}

	public void setBusinessTime(String businessTime) {
		this.businessTime = businessTime;
	}

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}


	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getMerchantShortName() {
		return merchantShortName;
	}

	public void setMerchantShortName(String merchantShortName) {
		this.merchantShortName = merchantShortName;
	}

	public String getMerchantDesc() {
		return merchantDesc;
	}

	public void setMerchantDesc(String merchantDesc) {
		this.merchantDesc = merchantDesc;
	}

	public String[] getSpecialService() {
		return specialService;
	}

	public void setSpecialService(String[] specialService) {
		this.specialService = specialService;
	}

	public BigDecimal getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(BigDecimal avgPrice) {
		this.avgPrice = avgPrice;
	}

	@Override
	public String toString() {
		return "UpdateMerchantInfoCommand [operatorId=" + operatorId + ", userId=" + userId + ", merchantNo="
				+ merchantNo + ", merchantShortName=" + merchantShortName + ", offficeContact=" + offficeContact
				+ ", officeTel=" + officeTel + ", businessTime=" + businessTime + ", businessAddress=" + businessAddress
				+ ", landmark=" + landmark + ", merchantDesc=" + merchantDesc + ", specialService="
				+ Arrays.toString(specialService) + ", avgPrice=" + avgPrice + "]";
	}


}
