package com.gl365.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gl365.app.dto.ResultDto;
import com.gl365.app.remote.merchant.MerchantRemoteService;
import com.gl365.app.remote.merchant.request.UpdateMerchantInfoReqDto;
import com.gl365.app.remote.merchant.request.UploadPicDto;
import com.gl365.app.remote.merchant.response.MerchantOperatorDto;
import com.gl365.app.service.MerchantOperatorService;
import com.gl365.app.service.RedisService;

@Service
public class MerchantOperatorServiceImpl implements MerchantOperatorService{

	@Autowired
	private RedisService redisService;
	@Autowired
	private MerchantRemoteService merchantRemoteService;
	
	@Override
	public Object updateMerchantInfo(UpdateMerchantInfoReqDto req,String currentOperatorId) throws Exception{
		
		MerchantOperatorDto operator = (MerchantOperatorDto) redisService.get(currentOperatorId);
		req.setOperatorId(operator.getOperatorId());
		req.setMerchantNo(operator.getMerchantNo());
		req.setUserId(operator.getUserId());
		return merchantRemoteService.updateMerchantInfo(req);
	}

	@Override
	public Object uploadPic(UploadPicDto req,String currentOperatorId) throws Exception {
		
		if(req.getPicUrl() == null && req.getPicUrl().length == 0) return ResultDto.errorResult();
		MerchantOperatorDto operator = (MerchantOperatorDto) redisService.get(currentOperatorId);
		Object result = this.merchantRemoteService.uploadPic(getUploadPicDto(req, operator));
		return result;
	}
	
	/**
	 * 
	* @Title: getUploadPicDto
	* @Description: 组装更新图片信息
	* @param  picUrl
	* @param  operator
	* @param @return   
	* @return UploadPicDto    返回类型
	* @throws
	 */
	private UploadPicDto getUploadPicDto(UploadPicDto picDto, MerchantOperatorDto operator) {
		picDto.setMerchantNo(operator.getMerchantNo());
		picDto.setOperatorId(operator.getOperatorId());
		picDto.setOperatorName(operator.getOperatorName());
		return picDto;
	}
	
}
