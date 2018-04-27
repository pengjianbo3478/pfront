package com.gl365.app.remote.settlequery.request;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

public class QueryTransDetailTotalReqDto {

	@ApiModelProperty(value = "商户号")
	private String merchantNo;
	@ApiModelProperty(value = "商户订单号")
	private String merchantOrderNo;
	@ApiModelProperty(value = "交易状态 1.已收款 2.已退款 为空查所有")
	private String payStatus;
	@ApiModelProperty(value = "支付方式 1.微信 2.支付宝 3.快捷支付")
	private String parentScene;
	@ApiModelProperty(value = "开始日期:yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fromDate;
	@ApiModelProperty(value = "结束日期 :yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate toDate;
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}
	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getParentScene() {
		return parentScene;
	}
	public void setParentScene(String parentScene) {
		this.parentScene = parentScene;
	}
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	
	
}
