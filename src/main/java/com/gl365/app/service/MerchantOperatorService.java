package com.gl365.app.service;

import com.gl365.app.remote.merchant.request.UpdateMerchantInfoReqDto;
import com.gl365.app.remote.merchant.request.UploadPicDto;

public interface MerchantOperatorService {
	
	Object updateMerchantInfo(UpdateMerchantInfoReqDto req,String currentOperatorId) throws Exception;
	Object uploadPic(UploadPicDto req,String currentOperatorId) throws Exception;
}
