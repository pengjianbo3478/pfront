package com.gl365.app.remote.payquery.response;

import java.io.Serializable;
import java.math.BigDecimal;

import com.gl365.app.utils.GsonUtils;

import io.swagger.annotations.ApiModelProperty;

public class QueryPagePayTransStatisTotalRespDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "返佣金额")
	private BigDecimal totalCommAmount;
	
	@ApiModelProperty(value = "实收金额")
	private BigDecimal totalMerchantSettleAmount;
	
	@ApiModelProperty(value = "订单金额")
	private BigDecimal totalTotalAmount;
	
	@ApiModelProperty(value = "乐豆")
	private BigDecimal totalBeanAmount;
	
	@ApiModelProperty(value = "返豆")
	private BigDecimal totalGiftAmount;
	
	@ApiModelProperty(value = "交易笔数")
	private int totalTxnCount;

	public BigDecimal getTotalCommAmount() {
		return totalCommAmount;
	}

	public void setTotalCommAmount(BigDecimal totalCommAmount) {
		this.totalCommAmount = totalCommAmount;
	}

	public BigDecimal getTotalMerchantSettleAmount() {
		return totalMerchantSettleAmount;
	}

	public void setTotalMerchantSettleAmount(BigDecimal totalMerchantSettleAmount) {
		this.totalMerchantSettleAmount = totalMerchantSettleAmount;
	}

	public BigDecimal getTotalTotalAmount() {
		return totalTotalAmount;
	}

	public void setTotalTotalAmount(BigDecimal totalTotalAmount) {
		this.totalTotalAmount = totalTotalAmount;
	}

	public BigDecimal getTotalBeanAmount() {
		return totalBeanAmount;
	}

	public void setTotalBeanAmount(BigDecimal totalBeanAmount) {
		this.totalBeanAmount = totalBeanAmount;
	}

	public BigDecimal getTotalGiftAmount() {
		return totalGiftAmount;
	}

	public void setTotalGiftAmount(BigDecimal totalGiftAmount) {
		this.totalGiftAmount = totalGiftAmount;
	}

	public int getTotalTxnCount() {
		return totalTxnCount;
	}

	public void setTotalTxnCount(int totalTxnCount) {
		this.totalTxnCount = totalTxnCount;
	}
	
	@Override
	public String toString() {
		return GsonUtils.toJson(this);
	}
	
}
