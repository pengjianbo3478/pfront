package com.gl365.app.remote.settlequery.response;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.remote.payquery.response.QueryCollectionDetailRespDTO;

import io.swagger.annotations.ApiModelProperty;

public class TransSettlementDetailRespDTO extends QueryCollectionDetailRespDTO{
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "支付流水号")
	private String payId;
	@ApiModelProperty(value = "商户订单号")
	private String merchantOrderNo;
	@ApiModelProperty(value = "商户号")
	private String merchantNo;
	@ApiModelProperty(value = "清算商户号")
	private String settleMerchantNo;
	@ApiModelProperty(value = "收款人姓名")
	private String operatorName;
	@ApiModelProperty(value = "支付场景大类")//1微信 2支付宝 3快捷支付
	private String parentScene;
	@ApiModelProperty(value = "支付场景大类名称")
	private String parentSceneName;
	@ApiModelProperty(value = "支付确认时间:yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate payTime;
	@ApiModelProperty(value = "交易状态 1.已收款 2.已退款")
	private String payStatus;
	@ApiModelProperty(value = "交易描述 1.已收款 2.已退款")
	private String payDesc;
	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
	}
	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}
	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getParentScene() {
		return parentScene;
	}
	public void setParentScene(String parentScene) {
		this.parentScene = parentScene;
	}
	public LocalDate getPayTime() {
		return payTime;
	}
	public void setPayTime(LocalDate payTime) {
		this.payTime = payTime;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getPayDesc() {
		return payDesc;
	}
	public void setPayDesc(String payDesc) {
		this.payDesc = payDesc;
	}
	
	
}
