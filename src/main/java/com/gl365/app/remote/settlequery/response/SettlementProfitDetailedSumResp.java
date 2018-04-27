package com.gl365.app.remote.settlequery.response;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModelProperty;
public class SettlementProfitDetailedSumResp {
	@ApiModelProperty("提现金额")
	private BigDecimal totalCashMoney;
	@ApiModelProperty("代付费")
	private BigDecimal totalFeeMoney;
	@ApiModelProperty("扣税费")
	private BigDecimal totalTaxMoney;
	@ApiModelProperty("实得金额(到账金额)")
	private BigDecimal totalRealMoney;

	public BigDecimal getTotalCashMoney() {
		return totalCashMoney;
	}

	public void setTotalCashMoney(BigDecimal totalCashMoney) {
		this.totalCashMoney = totalCashMoney;
	}

	public BigDecimal getTotalFeeMoney() {
		return totalFeeMoney;
	}

	public void setTotalFeeMoney(BigDecimal totalFeeMoney) {
		this.totalFeeMoney = totalFeeMoney;
	}

	public BigDecimal getTotalTaxMoney() {
		return totalTaxMoney;
	}

	public void setTotalTaxMoney(BigDecimal totalTaxMoney) {
		this.totalTaxMoney = totalTaxMoney;
	}

	public BigDecimal getTotalRealMoney() {
		return totalRealMoney;
	}

	public void setTotalRealMoney(BigDecimal totalRealMoney) {
		this.totalRealMoney = totalRealMoney;
	}
}
