package com.gl365.app.remote.payquery.response;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.utils.BigDecimaluitl;
import io.swagger.annotations.ApiModelProperty;
public class QueryPagePayTotalByOperatorDetailTotalRespDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 商户实得总额
	 */
	@ApiModelProperty(value = "商户实得总额")
	private BigDecimal merchantSettleAmountTotal;
	@ApiModelProperty(value = "交易日期：开始时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime payStartDate;
	@ApiModelProperty(value = "交易日期：结束时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime payEndDate;

	public String getMerchantSettleAmountTotal() {
		return BigDecimaluitl.setScaleStr(merchantSettleAmountTotal);
	}

	public void setMerchantSettleAmountTotal(BigDecimal merchantSettleAmountTotal) {
		this.merchantSettleAmountTotal = merchantSettleAmountTotal;
	}

	public LocalDateTime getPayStartDate() {
		return payStartDate;
	}

	public void setPayStartDate(LocalDateTime payStartDate) {
		this.payStartDate = payStartDate;
	}

	public LocalDateTime getPayEndDate() {
		return payEndDate;
	}

	public void setPayEndDate(LocalDateTime payEndDate) {
		this.payEndDate = payEndDate;
	}
}
