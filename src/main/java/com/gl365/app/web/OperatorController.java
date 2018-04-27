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
import com.gl365.app.dto.ResultDto;
import com.gl365.app.enums.ResultCodeEnum;
import com.gl365.app.remote.member.request.GetUserPhoneReqDto;
import com.gl365.app.remote.merchant.request.MerchantSaveOperatorReqDto;
import com.gl365.app.remote.merchant.request.MerchantUnBindOperatorReqDto;
import com.gl365.app.remote.merchant.request.MerchantUpdateOperatorReqDto;
import com.gl365.app.remote.validator.ValidateException;
import com.gl365.app.service.StaffOperatorService;
import com.gl365.app.utils.JsonUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600) // 跨域问题，与前端调试时打开
@Api(description = "操作员工信息相关接口")
@RestController
@RequestMapping("/businessAPI/operator")
public class OperatorController {
	
	private static final Logger LOG = LoggerFactory.getLogger(OperatorController.class);
	
	@SuppressWarnings("unused")
	private static Integer ADMIN = 1, SHOP_MANAGER = 2, OPERATOR = 3;

	@Autowired
	private StaffOperatorService staffOperatorService;

	/**
	 * 员工新增
	 * 
	 * @param command
	 * @return
	 */
	@PostMapping("/addStaff")
	@ApiOperation("员工新增")
	@Permis
	public Object addStaff(@RequestBody MerchantSaveOperatorReqDto req, HttpServletRequest request) {
		LOG.info("新增员工>>>入参：{}", JsonUtils.toJsonString(req));
		try {
			 Object result = staffOperatorService.addStaff(req, request);
			 LOG.info("新增员工>>>出参：{}", JsonUtils.toJsonString(result));
			 return result;
		} catch (ValidateException e) {
			return ResultDto.result(ResultCodeEnum.System.PARAM_ERROR, e.getLocalizedMessage(), null);
		} catch (Exception e) {
			LOG.error("新增员工>>>错误：{}", e);
			return ResultDto.errorResult();
		}
	}

	/**
	 * 员工新增
	 * 
	 * @param command
	 * @return
	 */
	@PostMapping("/add/check")
	@ApiOperation("员工新增")
	public Object saveOperatorcheck(HttpServletRequest request, @RequestBody GetUserPhoneReqDto req) {
		
		LOG.info("新增员工>>>入参：{}", JsonUtils.toJsonString(req));
		try {
			Object result = staffOperatorService.saveOperatorcheck(req);
			LOG.info("新增员工>>>出参：{}", JsonUtils.toJsonString(result));
			return result;
		} catch (Exception e) {
			LOG.error("新增员工>>>错误：{}", e);
			return ResultDto.errorResult();
		}
	}

	/**
	 * 员工修改
	 * 
	 * @param command
	 * @return
	 */
	@PostMapping("/update")
	@ApiOperation("员工修改")
	@Permis
	public Object updateOperator(@RequestBody MerchantUpdateOperatorReqDto req, HttpServletRequest request) {
		LOG.info("更新员工信息>>>入参：{}", JsonUtils.toJsonString(req));
		try {
			Object result = staffOperatorService.updateOperator(req, request);
			LOG.info("更新员工信息>>>出参：{}", JsonUtils.toJsonString(result));
			return result;
		} catch (ValidateException e) {
			return ResultDto.result(ResultCodeEnum.System.PARAM_ERROR, e.getLocalizedMessage(), null);
		} catch (Exception e) {
			LOG.error("更新员工信息>>>错误：{}", e);
			return ResultDto.errorResult();
		}
	}
	
	/**
	 * 员工解绑
	 * 
	 * @return
	 */
	@ApiOperation("员工解绑")
	@PostMapping("/updateStatus")
	@Permis
	public Object updateStatus(@RequestBody MerchantUnBindOperatorReqDto req, HttpServletRequest request) {
		
		LOG.info("员工解绑>>>员工ID：{}", req.getOperatorId());
		try {
			Object result = staffOperatorService.unBindOperator(req, request);
			LOG.info("员工解绑>>>出参：{}", JsonUtils.toJsonString(result));
			return result;
		} catch (Exception e) {
			LOG.error("员工解绑>>>错误：{}", e);
			return ResultDto.result(ResultCodeEnum.System.SYSTEM_ERROR);
		} 
	}
}
