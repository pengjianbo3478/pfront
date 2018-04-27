package com.gl365.app.remote.settlequery.response;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModelProperty;
public class ProfitDetailedSumResp {
	@ApiModelProperty("用户消费分润")
	private BigDecimal marcketAmount;
	@ApiModelProperty("激活粉丝奖励")
	private BigDecimal firstRewardAmount;
	@ApiModelProperty("用户打赏金额 ,只含现金金额，不包含乐豆金额")
	private BigDecimal tipRewardAmount;
	@ApiModelProperty("收银奖励")
	private BigDecimal cashierAmount;

	public BigDecimal getMarcketAmount() {
		return marcketAmount;
	}

	public void setMarcketAmount(BigDecimal marcketAmount) {
		this.marcketAmount = marcketAmount;
	}

	public BigDecimal getFirstRewardAmount() {
		return firstRewardAmount;
	}

	public void setFirstRewardAmount(BigDecimal firstRewardAmount) {
		this.firstRewardAmount = firstRewardAmount;
	}

	public BigDecimal getTipRewardAmount() {
		return tipRewardAmount;
	}

	public void setTipRewardAmount(BigDecimal tipRewardAmount) {
		this.tipRewardAmount = tipRewardAmount;
	}

	public BigDecimal getCashierAmount() {
		return cashierAmount;
	}

	public void setCashierAmount(BigDecimal cashierAmount) {
		this.cashierAmount = cashierAmount;
	}
}
