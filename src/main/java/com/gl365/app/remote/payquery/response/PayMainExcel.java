package com.gl365.app.remote.payquery.response;
import java.io.Serializable;
import java.time.LocalDateTime;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.enums.PaySceneEnum;
import com.gl365.app.enums.PayStatusEnum;
import io.swagger.annotations.ApiModelProperty;
public class PayMainExcel implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	@Excel(name = "交易时间", orderNum = "1", mergeVertical = false, isImportField = "payTime")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime payTime;
	/**
	 * 交易流水号
	 */
	@Excel(name = "交易单号", orderNum = "2", mergeVertical = false, isImportField = "payId")
	@ApiModelProperty(value = "交易流水号")
	private String payId;
	private String organOrderNo;
	/**
	 * 商户清算金额
	 * 
	 */
	@ApiModelProperty(value = "实收金额")
	@Excel(name = "实收金额（元）", orderNum = "3", mergeVertical = false, isImportField = "merchantSettleAmount")
	private String merchantSettleAmount;
	@ApiModelProperty(value = "返乐豆（个）")
	@Excel(name = "返乐豆（个）", orderNum = "4", mergeVertical = false, isImportField = "giftAmount")
	private String giftAmount;
	@ApiModelProperty(value = "平台服务费（元）")
	@Excel(name = "平台服务费（元）", orderNum = "5", mergeVertical = false, isImportField = "commAmount")
	private String commAmount;
	@ApiModelProperty(value = "订单金额")
	@Excel(name = "订单金额（元）", orderNum = "6", mergeVertical = false, isImportField = "totalAmount")
	private String totalAmount;
	@ApiModelProperty(value = "支付方式")
	@Excel(name = "支付方式", orderNum = "7", mergeVertical = false, isImportField = "sceneDesc")
	private String sceneDesc;
	private String scene;
	// 收款方式,暂无
	@Excel(name = "操作人员名称", orderNum = "8", mergeVertical = false, isImportField = "operatorName")
	@ApiModelProperty(value = "操作人员名称 ")
	private String operatorName;
	private String operator;
	@ApiModelProperty(value = "交易状态")
	@Excel(name = "交易状态", orderNum = "9", mergeVertical = false, isImportField = "payStatusDesc")
	private String payStatusDesc;
	private String payStatus;

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public LocalDateTime getPayTime() {
		return payTime;
	}

	public void setPayTime(LocalDateTime payTime) {
		this.payTime = payTime;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public String getOrganOrderNo() {
		return organOrderNo;
	}

	public void setOrganOrderNo(String organOrderNo) {
		this.organOrderNo = organOrderNo;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
		this.sceneDesc = PaySceneEnum.getDescByCode(scene);
	}

	public String getSceneDesc() {
		// 支付场景\n 0：直接支付（快捷支付）\n 1：B扫C支付\n 2：C扫B支付\n 3：POS',
		// if ("00".equals(scene)) {
		// payStatusDesc = "快捷支付";
		// } else if ("01".equals(scene)) {
		// payStatusDesc = "B扫C支付";
		// } else if ("02".equals(scene)) {
		// payStatusDesc = "C扫B支付";
		// } else if ("03".equals(scene)) {
		// payStatusDesc = "POS";
		// }else if ("04".equals(scene)) {
		// payStatusDesc = "微信直接支付";
		// } else if ("05".equals(scene)) {
		// payStatusDesc = "给乐APP发起微信H5支付";
		// }else if ("07".equals(scene)) {
		// payStatusDesc = "乐抢单";
		// } else {
		// payStatusDesc = "其它";
		// }
		return sceneDesc;
	}

	public String getPayStatusDesc() {
		// 交易状态\n 00：待支付\n 01：已支付\n 02：已撤销\n 03：已部分退货\n 04：已全额退货\n 05：已冲正',
		// if ("00".equals(payStatus)) {
		// payStatusDesc = "待支付";
		// } else if ("01".equals(payStatus)) {
		// payStatusDesc = "已支付";
		// } else if ("02".equals(payStatus)) {
		// payStatusDesc = "已撤销";
		// } else if ("03".equals(payStatus)) {
		// payStatusDesc = "已部分退货";
		// } else if ("04".equals(payStatus)) {
		// payStatusDesc = "已全额退货";
		// } else if ("05".equals(payStatus)) {
		// payStatusDesc = "已冲正";
		// }else if ("06".equals(payStatus)) {
		// payStatusDesc = "部分付款";
		// } else if ("07".equals(payStatus)) {
		// payStatusDesc = "部分付款撤销";
		// } else {
		// payStatusDesc = "其它";
		// }
		return payStatusDesc;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
		this.payStatusDesc = PayStatusEnum.getDescByCode(payStatus);
	}

	public String getMerchantSettleAmount() {
		return merchantSettleAmount;
	}

	public void setMerchantSettleAmount(String merchantSettleAmount) {
		this.merchantSettleAmount = merchantSettleAmount;
	}

	public String getGiftAmount() {
		return giftAmount;
	}

	public void setGiftAmount(String giftAmount) {
		this.giftAmount = giftAmount;
	}

	public String getCommAmount() {
		return commAmount;
	}

	public void setCommAmount(String commAmount) {
		this.commAmount = commAmount;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
}