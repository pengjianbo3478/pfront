package com.gl365.app.enums;
public enum QuitTypeEnum {
	LEAVE("离职", 1), ONJOB("在职", 0);
	private String desc;
	private int value;

	private QuitTypeEnum(String desc, int value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
