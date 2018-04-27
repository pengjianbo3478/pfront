package com.gl365.app.common;
public class PermisConstant {
	// 所有
	public static final String ALL = PermisConstant.ADMIN + PermisConstant.StoreManager + PermisConstant.Clerk;
	// 管理员
	public static final String ADMIN = "1";
	// 店长
	public static final String StoreManager = "2";
	// 店员
	public static final String Clerk = "3";
	// 离职
	public static final String Leave = "4";

	public static final String RULES(String... role) {
		StringBuffer sb = new StringBuffer();
		for (String str : role) {
			sb.append(str);
		}
		return sb.toString();
	}
}
