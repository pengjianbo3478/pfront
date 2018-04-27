package com.gl365.app.remote.settlequery.request;
import java.math.BigDecimal;
/**
 * 10.4.2交易结算-已结算统计合计
 *
 * @author Haixiang.Zheng
 * @since 2018/2/6
 */
public class MerchantSettlementSumForPC {
	private BigDecimal totalSettleAmount;// 到账金额

	public BigDecimal getTotalSettleAmount() {
		return totalSettleAmount;
	}

	public MerchantSettlementSumForPC setTotalSettleAmount(BigDecimal totalSettleAmount) {
		this.totalSettleAmount = totalSettleAmount;
		return this;
	}
}
