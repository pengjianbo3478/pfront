package com.gl365.app.enums;
public enum OperatorSignEnum {
	NO_SIGN(0, "无"), CASHIER_SIGN(1, "收银"), MANAGE_SIGN(2, "员工管理"), CASHIER_MANAGE_SIGN(3, "收银和员工管理"),;
	private Integer value;
	private String desc;

	private OperatorSignEnum(Integer value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
