package com.gl365.app.remote.merchant.request;

public class MerchantUpdateOperatorReqDto {

	private String operatorId;// 员工ID

	private String operatorName;// 员工名称

	private String telphone;// 联系电话

	private String idCard;// 身份证

	private String operatorNo;// 员工工号

	private String roleID;// 员工角色(1店长2操作员)

	private String parentId;// 店长ID

	private String status; // 禁用表示 0正常 1禁用
	
	private Boolean cashierBoo; //收银权限
	
	private Boolean staffmanagementBoo; // 员工管理权限

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getOperatorNo() {
		return operatorNo;
	}

	public void setOperatorNo(String operatorNo) {
		this.operatorNo = operatorNo;
	}

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public Boolean getCashierBoo() {
		return cashierBoo;
	}

	public void setCashierBoo(Boolean cashierBoo) {
		this.cashierBoo = cashierBoo;
	}

	public Boolean getStaffmanagementBoo() {
		return staffmanagementBoo;
	}

	public void setStaffmanagementBoo(Boolean staffmanagementBoo) {
		this.staffmanagementBoo = staffmanagementBoo;
	}
}
