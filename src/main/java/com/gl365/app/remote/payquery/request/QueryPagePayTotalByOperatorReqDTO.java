package com.gl365.app.remote.payquery.request;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.remote.settlequery.request.PageReq;
import io.swagger.annotations.ApiModelProperty;
public class QueryPagePayTotalByOperatorReqDTO extends PageReq {
	@ApiModelProperty(value = "商户号", hidden = true)
	private String merchantNo;
	@ApiModelProperty(value = "交易日期：开始时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime payStartDate;
	@ApiModelProperty(value = "交易日期：结果时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime payEndDate;

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
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
