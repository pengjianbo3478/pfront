package com.gl365.app.utils;
import java.math.BigDecimal;
import org.apache.commons.lang.StringUtils;
public class BigDecimaluitl {
	private static final String SALE_TYPE = "1";
	private static final String TRANS_TYPE = "1001,1101,2000,2100,3000,3100,3200,300,4101";
	private static final int _2 = 2;

	public static BigDecimal setScale(BigDecimal decimal, int arg0) {
		if (decimal == null) { return new BigDecimal(0).setScale(arg0, BigDecimal.ROUND_DOWN); }
		return decimal.setScale(arg0, BigDecimal.ROUND_DOWN);
	}

	public static String setScaleStr(BigDecimal decimal, int arg0) {
		return setScale(decimal, arg0).toString();
	}

	public static String setScaleStr(BigDecimal decimal) {
		return setScaleStr(decimal, _2);
	}

	/**
	 * 
	 * transType 为消费撤销 、消费冲正、消费退货，格式化金额为负金额
	 * 
	 * @param transType
	 *            1001：POS消费冲正 1101：网上消费冲正 2000：POS撤销 2100：网上消费撤销 3000：POS全额退货
	 *            3100：POS部分退货 3200：网上消费全额退货 3300：网上消费部分退货 4101：预授权完成确认冲正
	 * 
	 * @param amount
	 * 
	 * @return 金额BigDecimal类型
	 */
	public static BigDecimal formatAmtTransType(String transType, BigDecimal amount) {
		if (StringUtils.isNotBlank(transType) && amount != null && TRANS_TYPE.contains(transType)) { return amount.multiply(new BigDecimal(-1)); }
		return amount;
	}

	/**
	 * 
	 * transType 为消费撤销 、消费冲正、消费退货，格式化金额为负金额
	 * 
	 * @param transType
	 *            1001：POS消费冲正 1101：网上消费冲正 2000：POS撤销 2100：网上消费撤销 3000：POS全额退货
	 *            3100：POS部分退货 3200：网上消费全额退货 3300：网上消费部分退货 4101：预授权完成确认冲正
	 * 
	 * @param amount
	 * 
	 * @return 金额String类型，默认截取两位小数
	 */
	public static String formatAmtTransTypeStr(String transType, BigDecimal amount) {
		return setScaleStr(formatAmtTransType(transType, amount));
	}

	/**
	 * 
	 * saleType 单据类型:0：对应于消费确认、网上消费、预授权完成确认,1：对应于退货
	 * 
	 * @param transType
	 *            1：对应于退货
	 * 
	 * @param amount
	 * 
	 * @return 金额BigDecimal类型
	 */
	public static BigDecimal formatAmtSaleType(String saleType, BigDecimal amount) {
		if (StringUtils.isNotBlank(saleType) && amount != null && SALE_TYPE.contains(saleType)) { return amount.multiply(new BigDecimal(-1)); }
		return amount;
	}

	/**
	 * 
	 * saleType 单据类型:0：对应于消费确认、网上消费、预授权完成确认,1：对应于退货
	 * 
	 * @param transType
	 *            1：对应于退货
	 * 
	 * @param amount
	 * 
	 * @return 金额String类型，默认截取两位小数
	 */
	public static String formatAmtSaleTypeStr(String saleType, BigDecimal amount) {
		return setScaleStr(formatAmtSaleType(saleType, amount));
	}
}
