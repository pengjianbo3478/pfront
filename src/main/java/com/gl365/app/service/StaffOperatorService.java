package com.gl365.app.service;

import javax.servlet.http.HttpServletRequest;

import com.gl365.app.remote.member.request.GetUserPhoneReqDto;
import com.gl365.app.remote.merchant.request.MerchantSaveOperatorReqDto;
import com.gl365.app.remote.merchant.request.MerchantUnBindOperatorReqDto;
import com.gl365.app.remote.merchant.request.MerchantUpdateOperatorReqDto;
import com.gl365.app.remote.validator.ValidateException;

public interface StaffOperatorService {
	
	Object addStaff(MerchantSaveOperatorReqDto req,HttpServletRequest request) throws ValidateException,Exception;
	Object saveOperatorcheck(GetUserPhoneReqDto req) throws Exception;
	Object updateOperator(MerchantUpdateOperatorReqDto req,HttpServletRequest request) throws ValidateException,Exception;
	Object unBindOperator(MerchantUnBindOperatorReqDto req,HttpServletRequest request) throws Exception;
}
