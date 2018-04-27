package com.gl365.app.remote.settlequery.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class HomeSurveyTotalEarningsRespDto {

	@ApiModelProperty("清算日期 yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate settleDate;
	@ApiModelProperty("提成对象类型")
	private String ownerType;
	@ApiModelProperty("提成对象")
	private String ownerId;
	@ApiModelProperty("总的交易笔数")
	private Integer txnCount;
	@ApiModelProperty("总的交金额")
	private BigDecimal txnAmount;
	@ApiModelProperty("总的营销费")
	private BigDecimal totalMarcketFee;
	@ApiModelProperty("总的对象提成金额")
	private BigDecimal totalProfitFee;
	@ApiModelProperty("总的营销费提成")
	private BigDecimal profitMarcketFee;
	@ApiModelProperty("总的支付手续费提成")
	private BigDecimal profitPayFee;
	@ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;
	public LocalDate getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(LocalDate settleDate) {
		this.settleDate = settleDate;
	}
	public String getOwnerType() {
		return ownerType;
	}
	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public Integer getTxnCount() {
		return txnCount;
	}
	public void setTxnCount(Integer txnCount) {
		this.txnCount = txnCount;
	}
	public BigDecimal getTxnAmount() {
		return txnAmount;
	}
	public void setTxnAmount(BigDecimal txnAmount) {
		this.txnAmount = txnAmount;
	}
	public BigDecimal getTotalMarcketFee() {
		return totalMarcketFee;
	}
	public void setTotalMarcketFee(BigDecimal totalMarcketFee) {
		this.totalMarcketFee = totalMarcketFee;
	}
	public BigDecimal getTotalProfitFee() {
		return totalProfitFee;
	}
	public void setTotalProfitFee(BigDecimal totalProfitFee) {
		this.totalProfitFee = totalProfitFee;
	}
	public BigDecimal getProfitMarcketFee() {
		return profitMarcketFee;
	}
	public void setProfitMarcketFee(BigDecimal profitMarcketFee) {
		this.profitMarcketFee = profitMarcketFee;
	}
	public BigDecimal getProfitPayFee() {
		return profitPayFee;
	}
	public void setProfitPayFee(BigDecimal profitPayFee) {
		this.profitPayFee = profitPayFee;
	}
	public LocalDateTime getCreateTime() {
		return createTime;
	}
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
	
	
}
