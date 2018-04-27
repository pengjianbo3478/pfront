package com.gl365.app.remote.validator;

import com.gl365.app.enums.ResultCodeEnum;
import com.gl365.app.remote.merchant.request.MerchantOperatorReqDto;
import com.gl365.app.remote.merchant.request.MerchantUpdateOperatorReqDto;

public class OperatorValidate extends AbstractValidate {

	public static void validate(MerchantUpdateOperatorReqDto command) throws ValidateException {
		batchAcceptEmpty(command.getOperatorId(), command.getOperatorName(), command.getOperatorNo(), command.getRoleID(), command.getTelphone(), command.getIdCard());
	}

	public static void validate(MerchantOperatorReqDto command) throws ValidateException {
		if (null == command)
			throw new ValidateException(ResultCodeEnum.System.PARAM_NULL.getMsg());
		batchAcceptEmpty(command.getOperatorName(), command.getOperatorNo(), command.getRoleID(), command.getTelphone(), command.getUserId(), command.getAuthStatus().toString(), command.getIdCard());
	}

}
