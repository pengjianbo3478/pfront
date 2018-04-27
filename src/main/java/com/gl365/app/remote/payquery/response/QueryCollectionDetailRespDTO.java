package com.gl365.app.remote.payquery.response;

import java.io.Serializable;
import java.math.BigDecimal;

import com.gl365.app.utils.GsonUtils;

import io.swagger.annotations.ApiModelProperty;

public class QueryCollectionDetailRespDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "操作人员类型 1.收银员 2.商家")
	private String operatorType;
	@ApiModelProperty(value = "操作人员 ")
	private String operator;
	@ApiModelProperty(value = "操作人员名称 ")
	private String operatorName;
	@ApiModelProperty(value = "交易总金额 ")
	private BigDecimal totalAmount;
	@ApiModelProperty(value = "支付手续费 ")
	private BigDecimal payFee;
	@ApiModelProperty(value = "实扣金额 ")
	private BigDecimal cashAmount;
	@ApiModelProperty(value = "乐豆 ")
	private BigDecimal beanAmount;
	@ApiModelProperty(value = "返佣金额 ")
	private BigDecimal commAmount;
	@ApiModelProperty(value = "营销费 ")
	private BigDecimal marketFee;
	@ApiModelProperty(value = "返利金额 ")
	private BigDecimal giftAmount;
	@ApiModelProperty(value = "商户实得金额 ")
	private BigDecimal merchantSettleAmount;
	@ApiModelProperty(value = "交易笔数 ")
	private int txnCount;
	
	public String getOperatorType() {
		return operatorType;
	}
	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getPayFee() {
		return payFee;
	}
	public void setPayFee(BigDecimal payFee) {
		this.payFee = payFee;
	}
	public BigDecimal getCashAmount() {
		return cashAmount;
	}
	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}
	public BigDecimal getBeanAmount() {
		return beanAmount;
	}
	public void setBeanAmount(BigDecimal beanAmount) {
		this.beanAmount = beanAmount;
	}
	public BigDecimal getCommAmount() {
		return commAmount;
	}
	public void setCommAmount(BigDecimal commAmount) {
		this.commAmount = commAmount;
	}
	public BigDecimal getMarketFee() {
		return marketFee;
	}
	public void setMarketFee(BigDecimal marketFee) {
		this.marketFee = marketFee;
	}
	public BigDecimal getGiftAmount() {
		return giftAmount;
	}
	public void setGiftAmount(BigDecimal giftAmount) {
		this.giftAmount = giftAmount;
	}
	public BigDecimal getMerchantSettleAmount() {
		return merchantSettleAmount;
	}
	public void setMerchantSettleAmount(BigDecimal merchantSettleAmount) {
		this.merchantSettleAmount = merchantSettleAmount;
	}
	public int getTxnCount() {
		return txnCount;
	}
	public void setTxnCount(int txnCount) {
		this.txnCount = txnCount;
	}
	
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	@Override
	public String toString() {
		return GsonUtils.toJson(this);
	}
}
