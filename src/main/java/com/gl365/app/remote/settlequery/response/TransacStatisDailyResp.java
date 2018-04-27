package com.gl365.app.remote.settlequery.response;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.utils.BigDecimaluitl;
import io.swagger.annotations.ApiModelProperty;
/**
 * 首页交易统计-按日统计
 * 
 * @author dfs_518
 *
 *         2018年2月6日下午5:09:28
 */
public class TransacStatisDailyResp {
	@ApiModelProperty(value = "交易日期 :yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate payDate;
	@ApiModelProperty(value = "交易总笔数")
	private Integer txnCount;
	@ApiModelProperty(value = "交易总金额")
	private BigDecimal totalAmount;
	@ApiModelProperty(value = "客单价")
	private BigDecimal averageAmount;
	@ApiModelProperty(value = "实收总金额")
	private BigDecimal totalSettleAmount;

	public BigDecimal getTotalSettleAmount() {
		return totalSettleAmount;
	}

	public void setTotalSettleAmount(BigDecimal totalSettleAmount) {
		this.totalSettleAmount = totalSettleAmount;
	}

	public LocalDate getPayDate() {
		return payDate;
	}

	public void setPayDate(LocalDate payDate) {
		this.payDate = payDate;
	}

	public Integer getTxnCount() {
		return txnCount;
	}

	public void setTxnCount(Integer txnCount) {
		this.txnCount = txnCount;
	}

	public String getTotalAmount() {
		return BigDecimaluitl.setScaleStr(totalAmount);
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getAverageAmount() {
		return BigDecimaluitl.setScaleStr(averageAmount);
	}

	public void setAverageAmount(BigDecimal averageAmount) {
		this.averageAmount = averageAmount;
	}
}
