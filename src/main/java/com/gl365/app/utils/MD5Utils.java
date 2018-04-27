package com.gl365.app.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @author dfs_518
 *
 * 2017年9月7日下午12:21:46
 */
public final class MD5Utils {
	private static final Logger logger = LoggerFactory.getLogger(MD5Utils.class);
	private static final String CHARSET = "UTF-8";

	private MD5Utils() {
	}

	/**
	 * Returns a MessageDigest for the given <code>algorithm</code>.
	 * 
	 * @param algorithm
	 *            The MessageDigest algorithm name.
	 * @return An MD5 digest instance.
	 * @throws ServiceException
	 *             when a {@link java.security.NoSuchAlgorithmException} is
	 *             caught
	 */
	static MessageDigest getDigest() {
		try {
			return MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5算法不存在", e);
		}
	}

	/**
	 * Calculates the MD5 digest and returns the value as a 16 element
	 * <code>byte[]</code>.
	 * 
	 * @param data
	 *            Data to digest
	 * @return MD5 digest
	 */
	public static byte[] md5(byte[] data) {
		return getDigest().digest(data);
	}

	/**
	 * Calculates the MD5 digest and returns the value as a 16 element
	 * <code>byte[]</code>.
	 * 
	 * @param data
	 *            Data to digest
	 * @return MD5 digest
	 */
	public static byte[] md5(String data) throws Exception {
		return md5(data.getBytes(CHARSET));
	}

	/**
	 * Calculates the MD5 digest and returns the value as a 32 character hex
	 * string.
	 * 
	 * @param data
	 *            Data to digest
	 * @return MD5 digest as a hex string
	 */
	public static String md5Hex(byte[] data) {
		return HexUtil.toHexString(md5(data));
	}

	/**
	 * Calculates the MD5 digest and returns the value as a 32 character hex
	 * string.
	 * 
	 * @param data
	 *            Data to digest
	 * @return MD5 digest as a hex string
	 */
	public static String md5Hex(String data) {
		String result = null;
		try {
			result = HexUtil.toHexString(md5(data));
		}
		catch (Exception e) {
			logger.error(String.format("md5算法出错，加密源码：[%s]", data));
		}
		return result;
	}

	public static byte[] md5Hex(byte[] data, byte[] key) {
		MessageDigest md5 = getDigest();
		getDigest().update(data);
		return md5.digest(key);
	}

	public static String md5Hex(String data, String key) {
		if (StringUtils.isEmpty(data)) { return ""; }
		if (StringUtils.isEmpty(key)) {
			key = "";
		}
		return md5Hex(data + key);
	}
}