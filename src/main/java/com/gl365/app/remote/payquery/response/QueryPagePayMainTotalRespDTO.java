package com.gl365.app.remote.payquery.response;
import java.io.Serializable;
import java.math.BigDecimal;
import com.gl365.app.utils.BigDecimaluitl;
import io.swagger.annotations.ApiModelProperty;
public class QueryPagePayMainTotalRespDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 交易总额
	 */
	@ApiModelProperty(value = "交易总额")
	private BigDecimal totalAmountTotal;
	/**
	 * 现金总额
	 */
	@ApiModelProperty(value = "现金总额")
	private BigDecimal cashAmountTotal;
	/**
	 * 乐豆总额
	 */
	@ApiModelProperty(value = "乐豆总额")
	private BigDecimal beanAmountTotal;
	/**
	 * 返利总额
	 */
	@ApiModelProperty(value = "返利总额")
	private BigDecimal giftAmountTotal;
	/**
	 * 商户实得总额
	 */
	@ApiModelProperty(value = "商户实得总额")
	private BigDecimal merchantSettleAmountTotal;
	/**
	 * 不可返利合计
	 */
	@ApiModelProperty(value = "不可返利合计")
	private BigDecimal noBenefitAmountTotal;
	/**
	 * 平台服务费/返佣金额
	 */
	@ApiModelProperty(value = "平台服务费/返佣金额")
	private BigDecimal commAmountTotal;
	/**
	 * POS收款汇总
	 */
	@ApiModelProperty(value = "POS收款汇总")
	private BigDecimal posPayAmountTotal;
	/**
	 * 线上收款汇总/非POS
	 */
	@ApiModelProperty(value = "线上收款汇总/非POS")
	private BigDecimal onlinePayAmountTotal;

	public String getCommAmountTotal() {
		return BigDecimaluitl.setScaleStr(commAmountTotal);
	}

	public void setCommAmountTotal(BigDecimal commAmountTotal) {
		this.commAmountTotal = commAmountTotal;
	}

	public String getNoBenefitAmountTotal() {
		return BigDecimaluitl.setScaleStr(noBenefitAmountTotal);
	}

	public void setNoBenefitAmountTotal(BigDecimal noBenefitAmountTotal) {
		this.noBenefitAmountTotal = noBenefitAmountTotal;
	}

	public String getTotalAmountTotal() {
		return BigDecimaluitl.setScaleStr(totalAmountTotal);
	}

	public void setTotalAmountTotal(BigDecimal totalAmountTotal) {
		this.totalAmountTotal = totalAmountTotal;
	}

	public String getCashAmountTotal() {
		return BigDecimaluitl.setScaleStr(cashAmountTotal);
	}

	public void setCashAmountTotal(BigDecimal cashAmountTotal) {
		this.cashAmountTotal = cashAmountTotal;
	}

	public String getBeanAmountTotal() {
		return BigDecimaluitl.setScaleStr(beanAmountTotal);
	}

	public void setBeanAmountTotal(BigDecimal beanAmountTotal) {
		this.beanAmountTotal = beanAmountTotal;
	}

	public String getGiftAmountTotal() {
		return BigDecimaluitl.setScaleStr(giftAmountTotal);
	}

	public void setGiftAmountTotal(BigDecimal giftAmountTotal) {
		this.giftAmountTotal = giftAmountTotal;
	}

	public String getMerchantSettleAmountTotal() {
		return BigDecimaluitl.setScaleStr(merchantSettleAmountTotal);
	}

	public void setMerchantSettleAmountTotal(BigDecimal merchantSettleAmountTotal) {
		this.merchantSettleAmountTotal = merchantSettleAmountTotal;
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
