package com.gl365.app.remote.settlequery.response;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.utils.BigDecimaluitl;
import io.swagger.annotations.ApiModelProperty;
/**
 * 首页收益统计-按日统计
 * 
 * @author dfs_518
 *
 *         2018年2月6日下午5:08:55
 */
public class ProfitStatisDailyResp {
	@ApiModelProperty(value = "交易日期 :yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate payDate;
	@ApiModelProperty(value = "消费粉丝数")
	private Integer userCount;
	@ApiModelProperty(value = "交易总金额")
	private BigDecimal totalAmount;
	@ApiModelProperty(value = "收益总金额")
	private BigDecimal totalProfitAmount;

	public LocalDate getPayDate() {
		return payDate;
	}

	public void setPayDate(LocalDate payDate) {
		this.payDate = payDate;
	}

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

	public String getTotalAmount() {
		return BigDecimaluitl.setScaleStr(totalAmount);
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTotalProfitAmount() {
		return BigDecimaluitl.setScaleStr(totalProfitAmount);
	}

	public void setTotalProfitAmount(BigDecimal totalProfitAmount) {
		this.totalProfitAmount = totalProfitAmount;
	}
}
