package com.gl365.app.remote.payquery.response;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class QueryCollectionDetailByOperatorRespDTO extends QueryCollectionDetailRespDTO{
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "支付流水号")
	private String payId;
	@ApiModelProperty(value = "支付场景大类 1.微信支付 2.支付宝支付 3.快捷支付")//1.微信支付 2.支付宝支付 3.快捷支付
	private String parentScene;
	@ApiModelProperty(value = "支付场景大类名称 1.微信支付 2.支付宝支付 3.快捷支付")//1.微信支付 2.支付宝支付 3.快捷支付
	private String parentSceneName;
	@ApiModelProperty(value = "支付确定时间:yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate payTime;
	@ApiModelProperty(value = "交易状态 1.已收款 2.已退款")
	private String payStatus;
	@ApiModelProperty(value = "交易描述")
	private String payDesc;
	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
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
