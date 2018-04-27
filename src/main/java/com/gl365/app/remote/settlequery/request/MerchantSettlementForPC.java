package com.gl365.app.remote.settlequery.request;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.utils.GsonUtils;
/**
 * PC：10.4.1交易结算-已结算统计明细
 *
 * @author Haixiang.Zheng
 * @since 2018/2/6
 */
public class MerchantSettlementForPC {
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate settleDate;// 清算日期
	private BigDecimal settleAmount;// 到账金额
	private BigDecimal merchantSettleAmount;// 实收金额
	private BigDecimal totalAmount;// 订单金额
	private BigDecimal beanAmount;// 乐豆
	private BigDecimal giftAmount;// 返豆
	private String bankName;// 到账银行
	private String bankAccountNo;// 到账账号
	private BigDecimal wxSettleAmount;//微信支付结算金额
	private BigDecimal posSettleAmount;//POS刷卡结算金额
	private BigDecimal aliSettleAmount;//支付宝结算金额
	private BigDecimal commAmount;//返佣金额
	private BigDecimal status;//到账状态

	public LocalDate getSettleDate() {
		return settleDate;
	}

	public MerchantSettlementForPC setSettleDate(LocalDate settleDate) {
		this.settleDate = settleDate;
		return this;
	}

	public BigDecimal getSettleAmount() {
		return settleAmount;
	}

	public MerchantSettlementForPC setSettleAmount(BigDecimal settleAmount) {
		this.settleAmount = settleAmount;
		return this;
	}

	public BigDecimal getMerchantSettleAmount() {
		return merchantSettleAmount;
	}

	public MerchantSettlementForPC setMerchantSettleAmount(BigDecimal merchantSettleAmount) {
		this.merchantSettleAmount = merchantSettleAmount;
		return this;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public MerchantSettlementForPC setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
		return this;
	}

	public BigDecimal getBeanAmount() {
		return beanAmount;
	}

	public MerchantSettlementForPC setBeanAmount(BigDecimal beanAmount) {
		this.beanAmount = beanAmount;
		return this;
	}

	public BigDecimal getGiftAmount() {
		return giftAmount;
	}

	public MerchantSettlementForPC setGiftAmount(BigDecimal giftAmount) {
		this.giftAmount = giftAmount;
		return this;
	}

	public String getBankName() {
		return bankName;
	}

	public MerchantSettlementForPC setBankName(String bankName) {
		this.bankName = bankName;
		return this;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public MerchantSettlementForPC setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
		return this;
	}
	
	public BigDecimal getWxSettleAmount() {
		return wxSettleAmount;
	}

	public void setWxSettleAmount(BigDecimal wxSettleAmount) {
		this.wxSettleAmount = wxSettleAmount;
	}

	public BigDecimal getPosSettleAmount() {
		return posSettleAmount;
	}

	public void setPosSettleAmount(BigDecimal posSettleAmount) {
		this.posSettleAmount = posSettleAmount;
	}

	public BigDecimal getAliSettleAmount() {
		return aliSettleAmount;
	}

	public void setAliSettleAmount(BigDecimal aliSettleAmount) {
		this.aliSettleAmount = aliSettleAmount;
	}

	public BigDecimal getCommAmount() {
		return commAmount;
	}

	public void setCommAmount(BigDecimal commAmount) {
		this.commAmount = commAmount;
	}

	public BigDecimal getStatus() {
		return status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return GsonUtils.toJson(this);
	}
}
