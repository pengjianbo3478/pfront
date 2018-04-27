package com.gl365.app.common;
/**
 * < 响应的结果码 >
 *   	
 * 规则：
 * 100以下的错误码,系统保留,例如： 0-成功;1-签名失败;2-Token超时或无效
 * 101及以上错误码由接口自行定义。
 * 
 * @author hui.li 2017年4月12日 - 下午1:41:42
 * @Since  1.0
 */
public class ResultCodeConstant {
	public static final Integer SUCCESS = 0; // 成功
	public static final Integer INPUT_PARAM_ERROR = 2; // 输入参数错误
	public static final Integer SYSTEM_ERROR = 3; // 系统异常
	public static final Integer SYSTEM_TIME_OUT = 4; // 服务器繁忙
	public static final Integer SIGN_ERROR = 5; // 签名失败
	public static final Integer TOKEN_TIMEOUT = 6; // Token超时
	public static final Integer PARAM_NULL = 7; // 参数为空
	public static final Integer NEED_PAY_PWD = 101; // 需要支付密码
	public static final Integer UNKNOWN = 999; // 未知错误
}
