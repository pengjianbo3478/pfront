package com.gl365.app.remote.payquery.request;
import io.swagger.annotations.ApiModelProperty;
public class QueryPagePayMainReqDTO extends QueryPagePayTotalByOperatorReqDTO {
	@ApiModelProperty(value = "交易单号")
	private String payId;
	private String organOrderNo;
	@ApiModelProperty(value = "清算商户号")
	private String settleMerchantNo;
	@ApiModelProperty(value = "商户订单号")
	private String merchantOrderNo;
	@ApiModelProperty(value = "交易状态 1.已收款 2.已退款 为空查所有")
	private String payStatus;
	@ApiModelProperty(value = "支付方式 1.微信 2.支付宝 3.快捷支付")
	private String parentScene;
	@ApiModelProperty(value = "支付方式")
	private String scene;
	

	public String getOrganOrderNo() {
		return organOrderNo;
	}

	public void setOrganOrderNo(String organOrderNo) {
		if (null != organOrderNo && !"".equals(organOrderNo)) {
			this.organOrderNo = organOrderNo;
		}
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		if (null != payId && !"".equals(payId)) {
			this.payId = payId;
		}
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		if (null != merchantOrderNo && !"".equals(merchantOrderNo)) {
			this.merchantOrderNo = merchantOrderNo;
		}
	}

	public String getSettleMerchantNo() {
		return settleMerchantNo;
	}

	public void setSettleMerchantNo(String settleMerchantNo) {
		this.settleMerchantNo = settleMerchantNo;
	}

	public String getParentScene() {
		return parentScene;
	}

	public void setParentScene(String parentScene) {
		this.parentScene = parentScene;
	}
	
	
}
