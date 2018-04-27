package com.gl365.app.enums;
/**
 * api接口请求结果枚举 规则： 长度6位 |0000|00 前四位表示模块|后两位递增
 * 
 * 列如 系统类异常：0000** 用户类异常：1000**
 * 
 * @author dfs_519 2017年4月27日下午2:02:21
 */
public class ResultCodeEnum {
	public enum System {
		/**
		 * 系统保留100以下的错误码,提示可以调整,code不能变
		 */
		SUCCESS("000000", "成功"), NEED_LOGIN("000001", "请先登录"), PARAM_NULL("000002", "参数为空"), PARAM_ERROR("000003", "参数非法"), VERIFY_SIGN_FAIL("000004", "验签失败"), TOKEN_TIMEOUT("000005", "Token失效"), REQUEST_IS_NULL("000006", "错误请求"), NO_PERMISSION("000007", "您没有执行此操作的权限，请重新登录"), SYSTEM_DATA_EXECEPTION("000008", "系统数据异常"), KEY_ERROR_EXECEPTION("000009", "操作时间过长,请重新操作"), SYSTEM_TIME_OUT("000098", "请求频繁"), FORCED_LOGIN("b_forced_login", "您的账号在其它设备登录，请重新登录，为了您的账号安全，建议登录后修改密码"), SYSTEM_ERROR("000099", "亲，小乐开了会小差，待会再来吧！:)"),;
		private String code;
		private String msg;

		private System(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}

	public enum User {
		ID_PWD_MATCHING_ERROR("100001", "用户名或密码不正确"), LOG_COUNT_LIMIT_ERROR("100002", "设备超过当天使用数"), DEVICE_UNUSER_ERROR("100003", "设备被禁用或者被锁定"), NO_DEVICE_UNUSER_ERROR("100004", "设备不常用"), AUTH_CODE_ERROR("100005", "验证码错误"), PWD_MATCHING_ERROR("100006", "旧密码不正确"), PWD_SAME_ERROR("100007", "新旧密码不能相同"), PHONE_SAME_ERROR("100008", "手机号不能相同"), OLD_PHONE_ERROR("100009", "原号不正确"), NO_MERCHANT_INFO_ERROR("100010", "没有商户信息"), NO_USER_INFO_ERROR("100011", "手机号不存在,请确认手机号是否正确"), IMAGE_CODE_TIME_OUT("100012", "图片验证超时"), IMAGE_CODE_ERROR("100013", "图片验证不正确"), PASSWORD_COUNT_ERROR("100014", "输入密码错误次数过多,请使用其它方式登入"), USER_STATUS_ERROR("100015", "用户状态异常,可能被禁用或者已注销"), NO_ADD_USER_ERROR("100016", "该用户未关联商家,请联系商家管理员"), NO_MEMBER_INFO_ERROR("100017", "没有用户信息"), PHONE_NOREGIST_ERROR("100018", "手机号未注册"), OPERATOR_SIGN_ERROR("100019", "收银权限不足,请联系管理员"), IDENTITY_FAILD_ERROR("100019", "实名验证不通过,请确认信息后再验证"), OPERATOR_MANAGE_SIGN_ERROR("100020", "员工管理权限不足,请联系管理员"), IDENTITY_IS_EXIST("100022", "您输入的内容已被占用，请重新输入"), OPERATOR_NOT_MEMBER("100023", "该员工手机号尚未注册，请注册后再进行增加"), OPERATOR_NOT_OPEN_ACT("100024", "该员工未开通钱包功能，请通知员工开通钱包功能后再增加"), OPERATOR_NOT_LOGIN("100025", "您没有登录的权限"),;
		private String code;
		private String msg;

		private User(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}

	public enum Sms {
		;
		private String code;
		private String msg;

		private Sms(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}

	public enum Payment {
		/**
		 * 101及以上错误码由接口自行定义
		 */
		GET_MERCHANTCODE_FAIL("300001", "获取商户代码失败"), BARCODE_PAY_FAIL("300002", "收款失败"), BAR_PAY_QUERY_FAIL("300003", "收款查询失败"), CREATE_ORDER_FAIL("300004", "创建订单失败"), BARCODE_PAY_RUNNING("300005", "交易处理中"), TOTAL_AMOUNT_CANT_ZERO("300006", "收款金额不能为0"), REFUND_FAIL("300007", "退款失败"), REFUND_ING("300008", "该笔订单已在撤单中，请勿重复操作"),;
		private String code;
		private String msg;

		private Payment(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}

	public enum Merchant {
		;
		private String code;
		private String msg;

		private Merchant(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}

	public enum Customize {
		;
		private String code;
		private String msg;

		private Customize(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}

	public enum Settlement {
		GET_DAY_RESULT_FAIL("600001", "获取昨日收益失败"), GET_ORDER_LIST_FAIL("600002", "获取账单列表失败"), REFUND_PASSWORD_ERRPR("600003", "密码错误，请重新输入"), GET_MERCHANT_SETTLE_TOTAL("600004", "获取商户已结算汇总数据失败"), GET_MERCHANT_UNSETTLE_TOTAL("600005", "获取商户未结算汇总数据失败"), GET_MERCHANT_SETTLE_DATAIL("600006", "获取商户已结算详情数据失败"), GET_MERCHANT_UNSETTLE_DATAIL("600007", "获取商户未结算详情数据失败"), REFUND_FAIL("600008", "撤单失败"), GET_PROFIT_DETAIL_FAIL("600009", "查询收益明细失败"), GET_TRADE_COUNT_FAIL("600010", "查询粉丝消费笔数失败"),;
		private String code;
		private String msg;

		private Settlement(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}
}
