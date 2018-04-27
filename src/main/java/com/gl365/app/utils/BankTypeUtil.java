package com.gl365.app.utils;
public class BankTypeUtil {
	/**
	 * 工商银行
	 */
	private static final String ICBC = "001";
	/**
	 * 农业银行
	 */
	private static final String ABC = "002";
	/**
	 * 中国银行
	 */
	private static final String BOC = "003";
	/**
	 * 建设银行
	 */
	private static final String CCB = "004";
	/**
	 * 交通银行
	 */
	private static final String BCM = "005";
	/**
	 * 招商银行
	 */
	private static final String CMB = "006";
	/**
	 * 民生银行
	 */
	private static final String CMBC = "007";
	/**
	 * 浦发银行
	 */
	private static final String SPDB = "008";
	/**
	 * 兴业银行
	 */
	private static final String CIB = "009";
	/**
	 * 平安银行
	 */
	private static final String PAB = "010";
	/**
	 * 中信银行
	 */
	private static final String CITIC = "011";
	/**
	 * 光大银行
	 */
	private static final String CEB = "012";
	/**
	 * 广发银行
	 */
	private static final String GDB = "013";
	/**
	 * 邮政储蓄银行
	 */
	private static final String PSBC = "014";
	/**
	 * 华夏银行
	 */
	private static final String HXB = "015";
	/**
	 * 北京银行
	 */
	private static final String BJB = "016";
	/**
	 * 厦门银行
	 */
	private static final String XMB = "017";
	/**
	 * 上海银行
	 */
	private static final String BSH = "018";
	/**
	 * 其他
	 */
	private static final String OTHER = "019";

	public static String getBankType(String bankName) {
		if (bankName.indexOf("工商") != -1) {
			return ICBC;
		}
		else if (bankName.indexOf("农业") != -1) {
			return ABC;
		}
		else if (bankName.indexOf("中国银行") != -1) {
			return BOC;
		}
		else if (bankName.indexOf("建设") != -1) {
			return CCB;
		}
		else if (bankName.indexOf("交通") != -1) {
			return BCM;
		}
		else if (bankName.indexOf("招商") != -1) {
			return CMB;
		}
		else if (bankName.indexOf("民生") != -1) {
			return CMBC;
		}
		else if (bankName.indexOf("浦发") != -1) {
			return SPDB;
		}
		else if (bankName.indexOf("兴业") != -1) {
			return CIB;
		}
		else if (bankName.indexOf("平安") != -1) {
			return PAB;
		}
		else if (bankName.indexOf("中信") != -1) {
			return CITIC;
		}
		else if (bankName.indexOf("光大") != -1) {
			return CEB;
		}
		else if (bankName.indexOf("广发") != -1) {
			return GDB;
		}
		else if (bankName.indexOf("邮政储蓄") != -1) {
			return PSBC;
		}
		else if (bankName.indexOf("华夏") != -1) {
			return HXB;
		}
		else if (bankName.indexOf("北京银行") != -1) {
			return BJB;
		}
		else if (bankName.indexOf("厦门银行") != -1) {
			return XMB;
		}
		else if (bankName.indexOf("上海银行") != -1) {
			return BSH;
		}
		else {
			return OTHER;
		}
	}
}
