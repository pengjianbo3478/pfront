package com.gl365.app.remote.settlequery.response;
import java.math.BigDecimal;
import com.gl365.app.utils.BigDecimaluitl;
import io.swagger.annotations.ApiModelProperty;
/**
 * 首页收益统计-按月统计
 * @author dfs_518
 *
 * 2018年2月6日下午5:09:04
 */
public class ProfitStatisMonthResp {
	@ApiModelProperty(value = "交易月份")
	private String payMonth;
	@ApiModelProperty(value = "消费粉丝数")
	private Integer userCount;
	@ApiModelProperty(value = "交易总金额")
	private BigDecimal totalAmount;
	@ApiModelProperty(value = "收益总金额")
	private BigDecimal totalProfitAmount;

	public String getPayMonth() {
		return payMonth;
	}

	public void setPayMonth(String payMonth) {
		this.payMonth = payMonth;
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
