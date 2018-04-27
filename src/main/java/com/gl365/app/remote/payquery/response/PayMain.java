package com.gl365.app.remote.payquery.response;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl365.app.enums.PaySceneEnum;
import com.gl365.app.enums.PayStatusEnum;
import com.gl365.app.enums.PaymentTypeEnum;
import com.gl365.app.utils.BigDecimaluitl;
import io.swagger.annotations.ApiModelProperty;
public class PayMain implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime payTime;
	/**
	 * 支付公司订单号
	 */
	@ApiModelProperty(value = "支付公司订单号")
	private String organOrderNo;
	/**
	 * 商户清算金额
	 * 
	 */
	@ApiModelProperty(value = "实收金额")
	private BigDecimal merchantSettleAmount;
	@ApiModelProperty(value = "返乐豆（个）")
	private BigDecimal giftAmount;
	@ApiModelProperty(value = "平台服务费（元）")
	private BigDecimal commAmount;
	@ApiModelProperty(value = "订单金额")
	private BigDecimal totalAmount;
	public String getParentScene() {
		return parentScene;
	}

	public void setParentScene(String parentScene) {
		this.parentScene = parentScene;
	}

	public String getMarketFee() {
		return marketFee;
	}

	public void setMarketFee(String marketFee) {
		this.marketFee = marketFee;
	}

	@ApiModelProperty(value = "支付方式")
	private String sceneDesc;
	@ApiModelProperty(value = "支付场景大类")//1.微信支付 2.支付宝支付 3.快捷支付
	private String parentScene;
	private String scene;
	// 收款方式,暂无
	@ApiModelProperty(value = "操作人员名称 ")
	private String operatorName;
	private String operator;
	@ApiModelProperty(value = "交易状态")
	private String payStatusDesc;
	private String payStatus;
	private String payId;
	private String requestId;
	/** 请求交易日期 */
	@ApiModelProperty(value = "请求交易日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate requestDate;
	/**
	 * 查询流水号
	 */
	@ApiModelProperty(value = "查询流水号")
	private String prePayId;
	private String organCode;
	private String organMerchantNo;
	private String merchantNo;
	private String merchantName;
	private String terminal = StringUtils.EMPTY;
	private String merchantAgentNo;
	private String userAgentType;
	private String userAgentNo;
	/**
	 * 订单类型 1：正常订单（如果订单标题解析出来为空、或者是POS交易，则默认为1） 2：打赏订单 3：达人订单 4：网购订单
	 */
	@ApiModelProperty(value = "订单类型:1：正常订单（如果订单标题解析出来为空、或者是POS交易，则默认为1）,2：打赏订单,3：达人订单,4：网购订单")
	private String orderType;
	/**
	 * 发展会员商家店长
	 */
	@ApiModelProperty(value = "发展会员商家店长")
	private String userDevManager;
	/**
	 * 发展会员商家员工
	 */
	@ApiModelProperty(value = "发展会员商家员工")
	private String userDevStaff;
	/**
	 * 清算支付公司
	 */
	@ApiModelProperty(value = "清算支付公司")
	private String settleOrganNo;
	/**
	 * 发展商户机构上级机构
	 */
	@ApiModelProperty(value = "发展商户机构上级机构")
	private String parentAgentNo;
	/**
	 * 推广业务员
	 */
	@ApiModelProperty(value = "推广业务员")
	private String inviteAgentNo;
	/**
	 * 会员手机
	 */
	@ApiModelProperty(value = "会员手机")
	private String userMobile;
	/**
	 * 支付卡号
	 */
	@ApiModelProperty(value = "支付卡号")
	private String cardNo;
	private Short province;
	private Short city;
	private Short district;
	private String transType;
	private String transTypeDesc;
	private String merchantOrderTitle;
	private String merchentOrderDesc;
	private String merchantOrderNo;
	/**
	 * 被打赏员工 String 32 否
	 */
	@ApiModelProperty(value = "被打赏员工")
	private String rewardUserId; // 被打赏员工 String 32 否
	/**
	 * 被打赏原支付单号 String 32 否
	 */
	@ApiModelProperty(value = "")
	private String rewardPayId;
	private String userId;
	private String userName;
	private String cardIndex;
	private BigDecimal noBenefitAmount;
	/**
	 * 支付手续费类型（借贷）/卡类型
	 */
	@ApiModelProperty(value = "支付手续费类型（借贷）/卡类型")
	private String payFeeType;
	/**
	 * 支付手续费上限值
	 */
	@ApiModelProperty(value = "支付手续费上限值")
	private BigDecimal maxPayFee;
	private BigDecimal payFee;
	@ApiModelProperty(value = "营销费")
	private String marketFee;
	private BigDecimal cashAmount;
	private BigDecimal beanAmount;
	private BigDecimal coinAmount;
	private BigDecimal payFeeRate;
	@ApiModelProperty(value = "交易笔数")
	private int txnCount;
	/**
	 * 返佣率
	 */
	@ApiModelProperty(value = "返佣率")
	private BigDecimal commRate;
	private BigDecimal marcketFee;
	/**
	 * 返利率
	 */
	@ApiModelProperty(value = "返利率")
	private BigDecimal giftRate;
	private BigDecimal giftPoint;
	private String payDesc;
	private String isNotify;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;
	private String createBy;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifyTime;
	private String modifyBy;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endDate;
	/**
	 * 群组单号或达人活动单号
	 */
	@ApiModelProperty(value = "群组单号或达人活动单号")
	private String groupOrderId;
	/**
	 * 分单标志：\n0-主单\n1-子单（分单）
	 */
	@ApiModelProperty(value = "分单标志：\n0-主单\n1-子单（分单）")
	private String splitFlag;
	/**
	 * 群组发起人员应支付金额
	 */
	@ApiModelProperty(value = "群组发起人员应支付金额")
	private BigDecimal groupMainuserPay;
	/**
	 * 群平台支付总额
	 */
	@ApiModelProperty(value = "群平台支付总额")
	private BigDecimal groupPtPay;
	/**
	 * 群组发起人支付乐豆
	 */
	@ApiModelProperty(value = "群组发起人支付乐豆")
	private BigDecimal groupMainuserPayBean;
	/**
	 * 群成员本单返乐豆
	 */
	@ApiModelProperty(value = "群成员本单返乐豆")
	private BigDecimal groupGiftAmount;
	/**
	 * 群组消费商家
	 */
	@ApiModelProperty(value = "群组消费商家")
	private String groupMerchantNo;
	/**
	 * 通道订单号
	 */
	@ApiModelProperty(value = "通道订单号")
	private String transactionId;
	/**
	 * 融脉支付时间
	 */
	@ApiModelProperty(value = "融脉支付时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime organPayTime;
	/**
	 * 抵扣金额
	 */
	@ApiModelProperty(value = "抵扣金额")
	private BigDecimal decAmount;
	/**
	 * 加盟方式 1：联明商家（默认）2：合伙商家
	 */
	@ApiModelProperty(value = "加盟方式 1：联明商家（默认）2：合伙商家")
	private String joinType;
	private BigDecimal totalAmountStart;
	private BigDecimal totalAmountEnd;

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId == null ? null : payId.trim();
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId == null ? null : requestId.trim();
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}

	public String getPrePayId() {
		return prePayId;
	}

	public void setPrePayId(String prePayId) {
		this.prePayId = prePayId;
	}

	public String getOrganCode() {
		return organCode;
	}

	public void setOrganCode(String organCode) {
		this.organCode = organCode == null ? null : organCode.trim();
	}

	public String getOrganMerchantNo() {
		return organMerchantNo;
	}

	public void setOrganMerchantNo(String organMerchantNo) {
		this.organMerchantNo = organMerchantNo == null ? null : organMerchantNo.trim();
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo == null ? null : merchantNo.trim();
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName == null ? null : merchantName.trim();
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal == null ? null : terminal.trim();
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator == null ? null : operator.trim();
	}

	public String getMerchantAgentNo() {
		return merchantAgentNo;
	}

	public void setMerchantAgentNo(String merchantAgentNo) {
		this.merchantAgentNo = merchantAgentNo == null ? null : merchantAgentNo.trim();
	}

	public String getUserAgentType() {
		return userAgentType;
	}

	public void setUserAgentType(String userAgentType) {
		this.userAgentType = userAgentType == null ? null : userAgentType.trim();
	}

	public String getUserAgentNo() {
		return userAgentNo;
	}

	public void setUserAgentNo(String userAgentNo) {
		this.userAgentNo = userAgentNo == null ? null : userAgentNo.trim();
	}

	public Short getProvince() {
		return province;
	}

	public void setProvince(Short province) {
		this.province = province;
	}

	public Short getCity() {
		return city;
	}

	public void setCity(Short city) {
		this.city = city;
	}

	public Short getDistrict() {
		return district;
	}

	public void setDistrict(Short district) {
		this.district = district;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType == null ? null : transType.trim();
	}

	public String getTransTypeDesc() {
		transTypeDesc = PaymentTypeEnum.getDescByValue(this.transType);
		return transTypeDesc;
	}

	public void setTransTypeDesc(String transTypeDesc) {
		this.transTypeDesc = transTypeDesc;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene == null ? null : scene.trim();
	}

	public String getMerchantOrderTitle() {
		return merchantOrderTitle;
	}

	public void setMerchantOrderTitle(String merchantOrderTitle) {
		this.merchantOrderTitle = merchantOrderTitle == null ? null : merchantOrderTitle.trim();
	}

	public String getMerchentOrderDesc() {
		return merchentOrderDesc;
	}

	public void setMerchentOrderDesc(String merchentOrderDesc) {
		this.merchentOrderDesc = merchentOrderDesc == null ? null : merchentOrderDesc.trim();
	}

	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo == null ? null : merchantOrderNo.trim();
	}

	public String getRewardUserId() {
		return rewardUserId;
	}

	public void setRewardUserId(String rewardUserId) {
		this.rewardUserId = rewardUserId;
	}

	public String getRewardPayId() {
		return rewardPayId;
	}

	public void setRewardPayId(String rewardPayId) {
		this.rewardPayId = rewardPayId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getCardIndex() {
		return cardIndex;
	}

	public void setCardIndex(String cardIndex) {
		this.cardIndex = cardIndex == null ? null : cardIndex.trim();
	}

	public String getTotalAmount() {
		return BigDecimaluitl.setScaleStr(totalAmount);
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getNoBenefitAmount() {
		return BigDecimaluitl.setScaleStr(noBenefitAmount);
	}

	public void setNoBenefitAmount(BigDecimal noBenefitAmount) {
		this.noBenefitAmount = noBenefitAmount;
	}

	public String getPayFeeType() {
		return payFeeType;
	}

	public void setPayFeeType(String payFeeType) {
		this.payFeeType = payFeeType;
	}

	public String getPayFee() {
		return BigDecimaluitl.setScaleStr(payFee);
	}

	public void setPayFee(BigDecimal payFee) {
		this.payFee = payFee;
	}

	public String getCashAmount() {
		return BigDecimaluitl.setScaleStr(cashAmount);
	}

	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}

	public String getBeanAmount() {
		return BigDecimaluitl.setScaleStr(beanAmount);
	}

	public void setBeanAmount(BigDecimal beanAmount) {
		this.beanAmount = beanAmount;
	}

	public String getCoinAmount() {
		return BigDecimaluitl.setScaleStr(coinAmount);
	}

	public void setCoinAmount(BigDecimal coinAmount) {
		this.coinAmount = coinAmount;
	}

	public String getPayFeeRate() {
		return BigDecimaluitl.setScaleStr(payFeeRate);
	}

	public void setPayFeeRate(BigDecimal payFeeRate) {
		this.payFeeRate = payFeeRate;
	}

	public String getCommRate() {
		return BigDecimaluitl.setScaleStr(commRate);
	}

	public void setCommRate(BigDecimal commRate) {
		this.commRate = commRate;
	}

	public String getCommAmount() {
		return BigDecimaluitl.setScaleStr(commAmount);
	}

	public void setCommAmount(BigDecimal commAmount) {
		this.commAmount = commAmount;
	}

	public String getMarcketFee() {
		return BigDecimaluitl.setScaleStr(marcketFee);
	}

	public void setMarcketFee(BigDecimal marcketFee) {
		this.marcketFee = marcketFee;
	}

	public String getGiftRate() {
		return BigDecimaluitl.setScaleStr(giftRate);
	}

	public void setGiftRate(BigDecimal giftRate) {
		this.giftRate = giftRate;
	}

	/**
	 * 返利金额
	 * 
	 * @return
	 */
	public String getGiftAmount() {
		return BigDecimaluitl.setScaleStr(giftAmount);
	}

	/**
	 * 返利金额
	 * 
	 * @param giftAmount
	 */
	public void setGiftAmount(BigDecimal giftAmount) {
		this.giftAmount = giftAmount;
	}

	public String getGiftPoint() {
		return BigDecimaluitl.setScaleStr(giftPoint);
	}

	public void setGiftPoint(BigDecimal giftPoint) {
		this.giftPoint = giftPoint;
	}

	public String getMerchantSettleAmount() {
		return BigDecimaluitl.setScaleStr(merchantSettleAmount);
	}

	public void setMerchantSettleAmount(BigDecimal merchantSettleAmount) {
		this.merchantSettleAmount = merchantSettleAmount;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus == null ? null : payStatus.trim();
	}

	public String getPayDesc() {
		return payDesc;
	}

	public void setPayDesc(String payDesc) {
		this.payDesc = payDesc == null ? null : payDesc.trim();
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy == null ? null : createBy.trim();
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(LocalDateTime modifyTime) {
		this.modifyTime = modifyTime;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy == null ? null : modifyBy.trim();
	}

	public LocalDateTime getPayTime() {
		return payTime;
	}

	public void setPayTime(LocalDateTime payTime) {
		this.payTime = payTime;
	}

	public String getUserDevManager() {
		return userDevManager;
	}

	public void setUserDevManager(String userDevManager) {
		this.userDevManager = userDevManager;
	}

	public String getUserDevStaff() {
		return userDevStaff;
	}

	public void setUserDevStaff(String userDevStaff) {
		this.userDevStaff = userDevStaff;
	}

	public String getSettleOrganNo() {
		return settleOrganNo;
	}

	public void setSettleOrganNo(String settleOrganNo) {
		this.settleOrganNo = settleOrganNo;
	}

	public String getParentAgentNo() {
		return parentAgentNo;
	}

	public void setParentAgentNo(String parentAgentNo) {
		this.parentAgentNo = parentAgentNo;
	}

	public String getInviteAgentNo() {
		return inviteAgentNo;
	}

	public void setInviteAgentNo(String inviteAgentNo) {
		this.inviteAgentNo = inviteAgentNo;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getMaxPayFee() {
		return BigDecimaluitl.setScaleStr(maxPayFee);
	}

	public void setMaxPayFee(BigDecimal maxPayFee) {
		this.maxPayFee = maxPayFee;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	@Override
	public PayMain clone() {
		try {
			return (PayMain) super.clone();
		}
		catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return the isNotify
	 */
	public String getIsNotify() {
		return isNotify;
	}

	/**
	 * @param isNotify
	 *            the isNotify to set
	 */
	public void setIsNotify(String isNotify) {
		this.isNotify = isNotify;
	}

	/**
	 * 支付公司订单号
	 * 
	 * @return the organOrderNo
	 */
	public String getOrganOrderNo() {
		return organOrderNo;
	}

	/**
	 * 支付公司订单号
	 * 
	 * @param organOrderNo
	 *            the organOrderNo to set
	 */
	public void setOrganOrderNo(String organOrderNo) {
		this.organOrderNo = organOrderNo;
	}

	/**
	 * @return the startDate
	 */
	public LocalDateTime getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public LocalDateTime getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public String getGroupOrderId() {
		return groupOrderId;
	}

	public void setGroupOrderId(String groupOrderId) {
		this.groupOrderId = groupOrderId;
	}

	public String getSplitFlag() {
		return splitFlag;
	}

	public void setSplitFlag(String splitFlag) {
		this.splitFlag = splitFlag;
	}

	public String getGroupMainuserPay() {
		return BigDecimaluitl.setScaleStr(groupMainuserPay);
	}

	public void setGroupMainuserPay(BigDecimal groupMainuserPay) {
		this.groupMainuserPay = groupMainuserPay;
	}

	public String getGroupPtPay() {
		return BigDecimaluitl.setScaleStr(groupPtPay);
	}

	public void setGroupPtPay(BigDecimal groupPtPay) {
		this.groupPtPay = groupPtPay;
	}

	public String getGroupMainuserPayBean() {
		return BigDecimaluitl.setScaleStr(groupMainuserPayBean);
	}

	public void setGroupMainuserPayBean(BigDecimal groupMainuserPayBean) {
		this.groupMainuserPayBean = groupMainuserPayBean;
	}

	public String getGroupGiftAmount() {
		return BigDecimaluitl.setScaleStr(groupGiftAmount);
	}

	public void setGroupGiftAmount(BigDecimal groupGiftAmount) {
		this.groupGiftAmount = groupGiftAmount;
	}

	public String getGroupMerchantNo() {
		return groupMerchantNo;
	}

	public void setGroupMerchantNo(String groupMerchantNo) {
		this.groupMerchantNo = groupMerchantNo;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public LocalDateTime getOrganPayTime() {
		return organPayTime;
	}

	public void setOrganPayTime(LocalDateTime organPayTime) {
		this.organPayTime = organPayTime;
	}

	public String getDecAmount() {
		return BigDecimaluitl.setScaleStr(decAmount);
	}

	public void setDecAmount(BigDecimal decAmount) {
		this.decAmount = decAmount;
	}

	public String getJoinType() {
		return joinType;
	}

	public void setJoinType(String joinType) {
		this.joinType = joinType;
	}

	public String getTotalAmountStart() {
		return BigDecimaluitl.setScaleStr(totalAmountStart);
	}

	public void setTotalAmountStart(BigDecimal totalAmountStart) {
		this.totalAmountStart = totalAmountStart;
	}

	public String getTotalAmountEnd() {
		return BigDecimaluitl.setScaleStr(totalAmountEnd);
	}

	public void setTotalAmountEnd(BigDecimal totalAmountEnd) {
		this.totalAmountEnd = totalAmountEnd;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getSceneDesc() {
		sceneDesc = PaySceneEnum.getDescByCode(this.scene);
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

	public void setSceneDesc(String sceneDesc) {
		this.sceneDesc = sceneDesc;
	}

	public void setPayStatusDesc(String payStatusDesc) {
		this.payStatusDesc = payStatusDesc;
	}

	public String getPayStatusDesc() {
		payStatusDesc = PayStatusEnum.getDescByCode(this.payStatus);
		// // 交易状态\n 00：待支付\n 01：已支付\n 02：已撤销\n 03：已部分退货\n 04：已全额退货\n 05：已冲正',
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
}