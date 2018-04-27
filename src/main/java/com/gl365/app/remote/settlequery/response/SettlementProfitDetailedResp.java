package com.gl365.app.remote.settlequery.response;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
public class SettlementProfitDetailedResp {
	@ApiModelProperty("申请单号")
	private String voucherId;
	@ApiModelProperty("提成对象类型")
	private String ownerType;
	@ApiModelProperty("提成对象")
	private String ownerId;
	@ApiModelProperty("提现金额")
	private BigDecimal cashMoney;
	@ApiModelProperty("代付费")
	private BigDecimal feeMoney;
	@ApiModelProperty("扣税费")
	private BigDecimal taxMoney;
	@ApiModelProperty("实得金额(到账金额)")
	private BigDecimal realMoney;
	@ApiModelProperty("状态  0待授理(待授理),1已授理(已授理),2代付导出(授理中),3代付完成(提现完成),4提现失败,5申请撤回")
	private String state;
	@ApiModelProperty("状态描述    state=0,1,2：受理中,state=4,5：失败,state=3：已到账")
	private String stateDesc;
	@ApiModelProperty("申请时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;
	@ApiModelProperty("创建人")
	private String createBy;
	@ApiModelProperty("修改时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifyTime;
	@ApiModelProperty("修改人")
	private String modifyBy;
	@ApiModelProperty("审核时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime approveTime;
	@ApiModelProperty("审核人")
	private String approveName;
	@ApiModelProperty("银行卡号")
	private String bankAccountNo;
	@ApiModelProperty("银行卡户名")
	private String bankAccountName;
	@ApiModelProperty("开户支行银行名称")
	private String bankName;
	@ApiModelProperty("开户支行银行号")
	private String bankNo;
	@ApiModelProperty("身份证号")
	private String certNo;
	@ApiModelProperty("开户行银行总行")
	private String bankType;

	public String getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(String voucherId) {
		this.voucherId = voucherId;
	}

	public String getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public BigDecimal getCashMoney() {
		return cashMoney;
	}

	public void setCashMoney(BigDecimal cashMoney) {
		this.cashMoney = cashMoney;
	}

	public BigDecimal getFeeMoney() {
		return feeMoney;
	}

	public void setFeeMoney(BigDecimal feeMoney) {
		this.feeMoney = feeMoney;
	}

	public BigDecimal getTaxMoney() {
		return taxMoney;
	}

	public void setTaxMoney(BigDecimal taxMoney) {
		this.taxMoney = taxMoney;
	}

	public BigDecimal getRealMoney() {
		return realMoney;
	}

	public void setRealMoney(BigDecimal realMoney) {
		this.realMoney = realMoney;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateDesc() {
		return stateDesc;
	}

	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public LocalDateTime getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(LocalDateTime modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public LocalDateTime getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(LocalDateTime approveTime) {
		this.approveTime = approveTime;
	}

	public String getApproveName() {
		return approveName;
	}

	public void setApproveName(String approveName) {
		this.approveName = approveName;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
}
