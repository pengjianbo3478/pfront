package com.gl365.app.exception;
/**
 * < 参数基本校验异常 >
 * 
 * @since hui.li 2017年5月31日 下午4:57:04
 */
public class ValidateException extends Exception {
	private static final long serialVersionUID = 5079411424106991797L;

	public ValidateException() {
		super();
	}

	public ValidateException(String msg) {
		super(msg);
	}

	public ValidateException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ValidateException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getLocalizedMessage() {
		return getMessage();
	}
}
