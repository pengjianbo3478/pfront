package com.gl365.app.remote.merchant.request;

public class MerchantOperatorReqDto {

	private String userId; // 会员ID

	private String operatorName;// 用户名称

	private String telphone;// 联系电话

	private String idCard;// 身份证

	private String operatorNo;// 员工工号

	private String roleID;// 用户角色(1店长2操作员)

	private String parentId;// 店长ID

	private String status; // 禁用表示 0正常 1禁用

	private Integer authStatus;// 认证状态 0未认证 1身份证验证 2手机实名认证 3银行卡验证
	
	private Boolean cashierBoo; //收银权限
	
	private Boolean staffmanagementBoo; // 员工管理权限

	public MerchantOperatorReqDto() {
		super();
	}

	public MerchantOperatorReqDto(MerchantOperatorReqDto command) {
		super();
		this.userId = command.getUserId();
		this.operatorName = command.getOperatorName();
		this.telphone = command.getTelphone();
		this.idCard = command.getIdCard();
		this.operatorNo = command.getOperatorNo();
		this.roleID = command.getRoleID();
		this.parentId = command.getParentId();
		this.status = command.getStatus();
		this.authStatus = command.getAuthStatus();
		this.cashierBoo = command.getCashierBoo();
		this.staffmanagementBoo = command.getStaffmanagementBoo();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
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
