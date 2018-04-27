package com.gl365.app.enums;
import org.apache.commons.lang3.StringUtils;
public enum PayStatusEnum {
	WAIT_PAY("00", "待支付"), COMPLETE_PAY("01", "已支付"), CANCEL("02", "已撤销"), PART_RETURN("03", "已部分退货"), ALL_RETURN("04", "已全额退货"), REVERSE("05", "已冲正"), STATUS_06("06", "部分付款"), STATUS_07("07", "部分付款撤销"),;
	private final String code;
	private final String desc;

	private PayStatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public String getCode() {
		return code;
	}

	public static String getDescByCode(String code) {
		if (StringUtils.isBlank(code)) { return code; }
		for (PayStatusEnum e : values()) {
			if (e.getCode().equals(code)) { return e.getDesc(); }
		}
		return code;
	}
}
