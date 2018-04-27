package com.gl365.app.remote.settlequery.response;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
public class ProfitDetailedResp {
	@ApiModelProperty("交易时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime payTime;
	@ApiModelProperty("提成大类 1：营销费提成,3：首笔交易奖励,5：打赏奖励,6：收银奖励")
	private String parentProfitType;
	@ApiModelProperty("提成大类名称 1：用户消费分润,3：首笔交易奖励,5：打赏奖励,6：收银奖励")
	private String parentProfitName;
	@ApiModelProperty("提成分类 ")
	private String profitType;
	@ApiModelProperty("单据类型   0：对应于消费确认、网上消费、预授权完成确认,1：对应于退货")
	private String saleType;
	@ApiModelProperty("对象提成金额")
	private BigDecimal ownerProfitAmount;
	@ApiModelProperty("会员ID")
	private String userId;
	@ApiModelProperty("交易金额")
	private BigDecimal txnAmount;
	@ApiModelProperty("打赏乐豆")
	private BigDecimal beanAmount;
	@ApiModelProperty("收益状态描述 saleType=0时：已分润,saleType=1时：已退回")
	private String profitDesc;
	@ApiModelProperty("支付流水号")
	private String payId;
	@ApiModelProperty("给乐商户号")
	private String merchantNo;

	public LocalDateTime getPayTime() {
		return payTime;
	}

	public void setPayTime(LocalDateTime payTime) {
		this.payTime = payTime;
	}

	public String getParentProfitType() {
		return parentProfitType;
	}

	public void setParentProfitType(String parentProfitType) {
		this.parentProfitType = parentProfitType;
	}

	public String getParentProfitName() {
		return parentProfitName;
	}

	public void setParentProfitName(String parentProfitName) {
		this.parentProfitName = parentProfitName;
	}

	public String getProfitType() {
		return profitType;
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	public BigDecimal getOwnerProfitAmount() {
		return ownerProfitAmount;
	}

	public void setOwnerProfitAmount(BigDecimal ownerProfitAmount) {
		this.ownerProfitAmount = ownerProfitAmount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getTxnAmount() {
		return txnAmount;
	}

	public void setTxnAmount(BigDecimal txnAmount) {
		this.txnAmount = txnAmount;
	}

	public BigDecimal getBeanAmount() {
		return beanAmount;
	}

	public void setBeanAmount(BigDecimal beanAmount) {
		this.beanAmount = beanAmount;
	}

	public String getProfitDesc() {
		return profitDesc;
	}

	public void setProfitDesc(String profitDesc) {
		this.profitDesc = profitDesc;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
}
