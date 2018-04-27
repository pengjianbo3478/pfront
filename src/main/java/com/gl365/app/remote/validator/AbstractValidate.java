package com.gl365.app.remote.validator;

import org.apache.commons.lang.StringUtils;

import com.gl365.app.enums.ResultCodeEnum;

public abstract class AbstractValidate {

	protected static void accept(String data, Integer min, Integer max, boolean empty) throws ValidateException {
		if (empty)
			acceptEmpty(data);
		if (null != min && null != max)
			acceptLength(data, min, max);
	}

	protected static void batchAcceptEmpty(String... data) throws ValidateException {
		if (null != data && data.length > 0)
			for (String str : data) {
				acceptEmpty(str);
			}
	}

	protected static void acceptEmpty(String data) throws ValidateException {
		if (StringUtils.isEmpty(data)) {
			throw new ValidateException(ResultCodeEnum.System.PARAM_NULL.getMsg());
		}
	}

	protected static void acceptEmpty(String data, String message) throws ValidateException {
		if (StringUtils.isEmpty(data)) {
			throw new ValidateException(StringUtils.isEmpty(message) ? ResultCodeEnum.System.PARAM_NULL.getMsg() : message);
		}
	}

	protected static void acceptLength(String data, Integer min, Integer max) throws ValidateException {
		Integer length = StringUtils.isEmpty(data) ? 0 : data.length();
		if (length < min)
			throw new ValidateException(ResultCodeEnum.System.PARAM_ERROR.getMsg());
		if (length > max)
			throw new ValidateException(ResultCodeEnum.System.PARAM_ERROR.getMsg());
	}

}
