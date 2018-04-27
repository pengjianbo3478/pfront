package com.gl365.app.remote.payquery.response;
import java.io.Serializable;
import java.math.BigDecimal;
import com.gl365.app.utils.BigDecimaluitl;
import com.gl365.app.utils.GsonUtils;
public class QueryPayTotalByOperatorTotalRespDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 商户实得总额
	 */
	private BigDecimal merchantSettleAmountTotal;
	/**
	 * 返利总额
	 */
	private BigDecimal giftAmountTotal;
	/**
	 * 平台服务费/返佣金额
	 */
	private BigDecimal commAmountTotal;
	/**
	 * 交易总额
	 */
	private BigDecimal totalAmountTotal;
	/**
	 * POS收款汇总
	 */
	private BigDecimal posPayAmountTotal;
	/**
	 * 线上收款汇总/非POS
	 */
	private BigDecimal onlinePayAmountTotal;

	public String getMerchantSettleAmountTotal() {
		return BigDecimaluitl.setScaleStr(merchantSettleAmountTotal);
	}

	public void setMerchantSettleAmountTotal(BigDecimal merchantSettleAmountTotal) {
		this.merchantSettleAmountTotal = merchantSettleAmountTotal;
	}

	public String getGiftAmountTotal() {
		return BigDecimaluitl.setScaleStr(giftAmountTotal);
	}

	public void setGiftAmountTotal(BigDecimal giftAmountTotal) {
		this.giftAmountTotal = giftAmountTotal;
	}

	public String getCommAmountTotal() {
		return BigDecimaluitl.setScaleStr(commAmountTotal);
	}

	public void setCommAmountTotal(BigDecimal commAmountTotal) {
		this.commAmountTotal = commAmountTotal;
	}

	public String getTotalAmountTotal() {
		return BigDecimaluitl.setScaleStr(totalAmountTotal);
	}

	public void setTotalAmountTotal(BigDecimal totalAmountTotal) {
		this.totalAmountTotal = totalAmountTotal;
	}

	@Override
	public String toString() {
		return GsonUtils.toJson(this);
	}

	public String getPosPayAmountTotal() {
		return BigDecimaluitl.setScaleStr(posPayAmountTotal);
	}

	public void setPosPayAmountTotal(BigDecimal posPayAmountTotal) {
		this.posPayAmountTotal = posPayAmountTotal;
	}

	public String getOnlinePayAmountTotal() {
		return BigDecimaluitl.setScaleStr(onlinePayAmountTotal);
	}

	public void setOnlinePayAmountTotal(BigDecimal onlinePayAmountTotal) {
		this.onlinePayAmountTotal = onlinePayAmountTotal;
	}
}
