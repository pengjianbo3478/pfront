package com.gl365.app.remote.merchant.response;
import java.math.BigDecimal;
public class MerchantBasicsInfoDto {
	private String merchantNo;// 商户号
	private String merchantName;// 商家名称
	private String merchantShortName;// 商户简称
	private BigDecimal saleRate;// 返利率
	private String mainImage;// 商家主图
	private String logoImage;// 商家logo图片
	private boolean isOnLinePay = false;// 是否线上支付
	// 组装查询当前商户的用户信息
	private Boolean cashierBoo;// 收银权限
	private Boolean staffmanagementBoo;// 员工管理权限
	private String role;// 1为管理员，2为店长，3为操作员 ， 4离职状态
	private BigDecimal minSaleRate;
	private BigDecimal maxSaleRate;
	private BigDecimal defaultSaleRatePre;
	private Integer noBenefit;

	public Integer getNoBenefit() {
		return noBenefit;
	}

	public void setNoBenefit(Integer noBenefit) {
		this.noBenefit = noBenefit;
	}

	public boolean isOnLinePay() {
		return isOnLinePay;
	}

	public void setOnLinePay(boolean isOnLinePay) {
		this.isOnLinePay = isOnLinePay;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getLogoImage() {
		return logoImage;
	}

	public void setLogoImage(String logoImage) {
		this.logoImage = logoImage;
	}

	public String getMerchantShortName() {
		return merchantShortName;
	}

	public void setMerchantShortName(String merchantShortName) {
		this.merchantShortName = merchantShortName;
	}

	public BigDecimal getSaleRate() {
		return saleRate;
	}

	public void setSaleRate(BigDecimal saleRate) {
		this.saleRate = saleRate;
	}

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	public Boolean getCashierBoo() {
		return cashierBoo;
	}

	public void setCashierBoo(Boolean cashierBoo) {
		this.cashierBoo = cashierBoo;
	}

	public Boolean getStaffmanagementBoo() {
		return staffmanagementBoo;
	}

	public void setStaffmanagementBoo(Boolean staffmanagementBoo) {
		this.staffmanagementBoo = staffmanagementBoo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public BigDecimal getMinSaleRate() {
		return minSaleRate;
	}

	public void setMinSaleRate(BigDecimal minSaleRate) {
		this.minSaleRate = minSaleRate;
	}

	public BigDecimal getMaxSaleRate() {
		return maxSaleRate;
	}

	public void setMaxSaleRate(BigDecimal maxSaleRate) {
		this.maxSaleRate = maxSaleRate;
	}

	public BigDecimal getDefaultSaleRatePre() {
		return defaultSaleRatePre;
	}

	public void setDefaultSaleRatePre(BigDecimal defaultSaleRatePre) {
		this.defaultSaleRatePre = defaultSaleRatePre;
	}
}
