package com.gl365.app.utils;
import org.apache.commons.lang.StringUtils;
public class MaskUtils {
	/**
	 * 银行卡号掩码
	 * @param source
	 * @return
	 */
	public static String bankCardNoMask(String source) {
		if (StringUtils.isBlank(source) || source.length() <= 10) { return source; }
		StringBuffer rlt = new StringBuffer("**** **** **** ");
		rlt.append(source.substring(source.length() - 4, source.length()));
		return rlt.toString();
	}

	/**
	 * 姓名掩码
	 * @param source
	 * @return
	 */
	public static String nameMask(String source) {
		if (StringUtils.isBlank(source) || source.trim().length() < 2) { return source; }
		StringBuffer rlt = new StringBuffer("**");
		rlt.append(source.substring(source.length() - 1, source.length()));
		return rlt.toString();
	}

	/**
	 * 手机掩码
	 * @param source
	 * @return
	 */
	public static String mobileMask(String source) {
		if (StringUtils.isBlank(source) || source.trim().length() < 11) { return source; }
		StringBuffer rlt = new StringBuffer("");
		rlt.append(source.substring(0, 3)).append("****").append(source.substring(source.length() - 4, source.length()));
		return rlt.toString();
	}
}
