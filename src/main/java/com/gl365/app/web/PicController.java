package com.gl365.app.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.app.common.Permis;
import com.gl365.app.common.PermisConstant;
import com.gl365.app.dto.ResultDto;
import com.gl365.app.remote.merchant.request.UploadPicDto;
import com.gl365.app.service.MerchantOperatorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600) // 跨域问题，与前端调试时打开
@Api(description="商家图片相关接口")
@RestController
@RequestMapping("/businessAPI/merchant/pic")
public class PicController extends BaseController{

	private static final Logger LOG = LoggerFactory.getLogger(PicController.class);
	@Autowired
	private MerchantOperatorService merchantOperatorService;
	
	@ApiOperation(value = "图片上传记录图片数组路径按数组顺序保存")
	@Permis(permission = PermisConstant.ADMIN+PermisConstant.StoreManager)
	@PostMapping("/upload")
	public Object uploadPic(HttpServletRequest request, @RequestBody UploadPicDto req) {
		
		LOG.debug("图片上传入参>>> :{}",req);
		try {
			Object result = merchantOperatorService.uploadPic(req, getCurrentOperatorId());
			return result;
		} catch (Exception e) {
			LOG.error("====uploadPic is Exception", e);
			return ResultDto.errorResult();
		}
	}
}
