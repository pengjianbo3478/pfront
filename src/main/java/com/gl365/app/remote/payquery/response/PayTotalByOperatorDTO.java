package com.gl365.app.remote.payquery.response;
import java.io.Serializable;
import java.math.BigDecimal;
import com.gl365.app.utils.BigDecimaluitl;
import com.gl365.app.utils.GsonUtils;
import io.swagger.annotations.ApiModelProperty;
public class PayTotalByOperatorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "操作人员类型 ")
	private String operateType;
	@ApiModelProperty(value = "操作人员 ")
	private String operator;
	@ApiModelProperty(value = "操作人员名称 ")
	private String operatorName;
	/**
	 * 商户实得总额
	 */
	@ApiModelProperty(value = "商户实得总额")
	private BigDecimal merchantSettleAmountTotal;
	/**
	 * 返利总额
	 */
	@ApiModelProperty(value = "返利总额")
	private BigDecimal giftAmountTotal;
	/**
	 * 平台服务费/返佣金额
	 */
	@ApiModelProperty(value = "平台服务费/返佣金额")
	private BigDecimal commAmountTotal;
	/**
	 * 交易总额
	 */
	@ApiModelProperty(value = "交易总额")
	private BigDecimal totalAmountTotal;
	/**
	 * 交易总额
	 */
	@ApiModelProperty(value = "交易总额")
	private BigDecimal totalAmount;
	/**
	 * 订单数
	 */
	@ApiModelProperty(value = "订单数")
	private Long txnCount;

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getTotalAmountTotal() {
		return BigDecimaluitl.setScaleStr(totalAmountTotal);
	}

	public void setTotalAmountTotal(BigDecimal totalAmountTotal) {
		this.totalAmountTotal = totalAmountTotal;
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

	@Override
	public String toString() {
		return GsonUtils.toJson(this);
	}

	public Long getTxnCount() {
		return txnCount;
	}

	public void setTxnCount(Long txnCount) {
		this.txnCount = txnCount;
	}

	public String getCommAmountTotal() {
		return BigDecimaluitl.setScaleStr(commAmountTotal);
	}

	public void setCommAmountTotal(BigDecimal commAmountTotal) {
		this.commAmountTotal = commAmountTotal;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
}
