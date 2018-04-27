package com.gl365.app.utils;
import java.math.BigDecimal;
import java.math.RoundingMode;
public class AmountTransferUtils {
	private static final BigDecimal exchangeRate = new BigDecimal(100);
	private static final BigDecimal _0 = new BigDecimal(0);

	public static BigDecimal fen2yuan(BigDecimal fen) {
		if (fen == null) { return null; }
		if (_0.equals(fen)) { return _0; }
		return fen.divide(exchangeRate).setScale(2);
	}

	public static BigDecimal yuan2fen(BigDecimal yuan) {
		if (yuan == null) { return null; }
		return new BigDecimal(yuan.multiply(exchangeRate).intValue());
	}

	/**
	 * 四舍五入保留2位小数
	 * @param arg
	 * @return
	 */
	public static BigDecimal formatComma2BigDecimal(BigDecimal arg) {
		if (arg == null) return new BigDecimal("0.00");
		BigDecimal decimal = arg.setScale(2, RoundingMode.HALF_UP);
		return decimal;
	}
}
