package com.gl365.app.enums;
/**
 * Created by xty on 2017/7/3.
 */
public enum SMSTypeEnum {
	/**
	 * 用到的自行添加
	 * 
	 * 业务类型
	 * 0：注册
	 * 1：修改手机号给新手机号
	 * 2：登录
	 * 3：C端用户忘记密码
	 * 4：修改手机号给旧手机号
	 * 5：b端绑定操作员
	 * 6：b端设置银行卡
	 * 7：B端管理员忘记密码
	 */
	UPDATE_PASSWORD(3, "普通用户忘记密码"), UPDATE_PASSWORD_ADMIN(7, "管理员忘记密码"),;
	private int code;
	private String msg;

	SMSTypeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
