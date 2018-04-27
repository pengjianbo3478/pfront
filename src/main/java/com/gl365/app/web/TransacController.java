package com.gl365.app.web;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.app.common.HttpParamConstant;
import com.gl365.app.common.Log;
import com.gl365.app.common.Permis;
import com.gl365.app.common.PermisConstant;
import com.gl365.app.dto.ResultDto;
import com.gl365.app.enums.ResultCodeEnum;
import com.gl365.app.remote.member.MemberRemoteService;
import com.gl365.app.remote.member.request.UserUpdateDto;
import com.gl365.app.remote.member.request.ValidatePwdReq;
import com.gl365.app.remote.merchant.response.MerchantOperatorDto;
import com.gl365.app.remote.order.OrderRemoteService;
import com.gl365.app.remote.order.request.OrderRefundReq;
import com.gl365.app.remote.order.request.RefundReq;
import com.gl365.app.remote.payquery.PayQueryRemoteService;
import com.gl365.app.remote.payquery.request.QueryPagePayMainReqDTO;
import com.gl365.app.remote.payquery.request.QueryPagePayTotalByOperatorDetailReqDTO;
import com.gl365.app.remote.payquery.request.QueryPagePayTotalByOperatorReqDTO;
import com.gl365.app.remote.payquery.response.PayMain;
import com.gl365.app.remote.payquery.response.PayMainExcel;
import com.gl365.app.remote.payquery.response.PayTotalByOperatorDTO;
import com.gl365.app.remote.payquery.response.QueryPagePayMainRespDTO;
import com.gl365.app.remote.payquery.response.QueryPagePayTotalByOperatorDetailRespDTO;
import com.gl365.app.remote.payquery.response.QueryPagePayTotalByOperatorRespDTO;
import com.gl365.app.service.RedisService;
import com.gl365.app.utils.JsonUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins = "*", maxAge = 3600) // 跨域问题，与前端调试时打开
@Api(description = "交易相关接口")
@RestController
@RequestMapping("/businessAPI/transac")
public class TransacController {
	private static final Logger logger = LoggerFactory.getLogger(TransacController.class);
	private static final int _1_ = 1;
	@Autowired
	private MemberRemoteService memberRemoteService;
	@Autowired
	private PayQueryRemoteService payQueryRemoteService;
	@Autowired
	private OrderRemoteService orderRemoteService;
	@Resource
	private RedisService redisService;

	@Permis
	@Log
	@ApiOperation(value = "交易明细列表", httpMethod = "POST", notes = "返回data参数如下Model说明：", response = QueryPagePayMainRespDTO.class)
	@PostMapping("/queryPagePayMain")
	public ResultDto<QueryPagePayMainRespDTO> transacStatisDaily(HttpServletRequest request, @RequestBody QueryPagePayMainReqDTO req) {
		String operatorId = (String) request.getSession().getAttribute(HttpParamConstant.Session.GL_APP_USER_ID);
		MerchantOperatorDto operator = (MerchantOperatorDto) redisService.get(operatorId);

//		if (operator.getLinkType() == _1_) {
//			req.setMerchantNo(operator.getParentMerchantNo());
//		} else {
			req.setMerchantNo(operator.getMerchantNo());
//		}
		logger.info("queryPagePayMain ===> payqueryService.queryPagePayMain req:{}",JsonUtils.toJsonString(req));
		QueryPagePayMainRespDTO respDTO = payQueryRemoteService.queryPagePayMain(req);
		logger.info("queryPagePayMain ===> payqueryService.queryPagePayMain resp:{}",JsonUtils.toJsonString(respDTO));
		
		String result = respDTO.getResultCode();
		String description = respDTO.getResultDesc();
		if (ResultCodeEnum.System.SUCCESS.getCode().equals(result) && respDTO.getResultData() != null) {
			List<PayMain> paymains = respDTO.getResultData().getList();
			if (CollectionUtils.isNotEmpty(paymains)) {
				List<String> userIds = new ArrayList<String>();
				for (PayMain payMain : paymains) {
					if(StringUtils.isNotBlank(payMain.getOperator())){
						userIds.add(payMain.getOperator());
					}else{
						// 产品说,此处除了有明确员工的,其它都叫做商家,此时前端查看详细按钮屏蔽  20180228
						payMain.setOperatorName("商家");
					}
				}
				if (CollectionUtils.isNotEmpty(userIds)) {
					List<UserUpdateDto> dtos = memberRemoteService.getUserByuserIdList(userIds);
					Map<String, String> userMaps = dtos.stream().collect(Collectors.toMap(UserUpdateDto::getUserId, UserUpdateDto::getRealName));
					for (PayMain payMain : paymains) {
						String key = payMain.getOperator();
						if (userMaps.get(key) != null) {
							payMain.setOperatorName(userMaps.get(key));
						} else {
							payMain.setOperatorName("商家");
						}
					}
				}
			}
			respDTO.getResultData().setList(paymains);
		}
		respDTO.setResultCode(null);
		respDTO.setResultDesc(null);
		
		return new ResultDto<>(result, description, respDTO);
	}

	@Permis
	@Log
	@ApiOperation(value = "交易明细列表Excel", httpMethod = "POST", notes = "返回data参数如下Model说明：", response = QueryPagePayMainRespDTO.class)
	@PostMapping("/queryPagePayMainExcel")
	public void transacStatisDailyExcel(HttpServletRequest request, @RequestBody QueryPagePayMainReqDTO req,
			HttpServletResponse response) throws Exception {
		List<PayMainExcel> target = new ArrayList<>();
		ResultDto<QueryPagePayMainRespDTO> result = transacStatisDaily(request, req);
		if (result != null && result.getData() != null && result.getData().getResultData() != null
				&& CollectionUtils.isNotEmpty(result.getData().getResultData().getList())) {
			List<PayMain> source = result.getData().getResultData().getList();
			for (PayMain payMain : source) {
				PayMainExcel payMainExcel = new PayMainExcel();
				BeanUtils.copyProperties(payMain, payMainExcel);
				target.add(payMainExcel);
			}
		}

		// 告诉浏览器用什么软件可以打开此文件
		response.setHeader("content-Type", "application/vnd.ms-excel");
		// 下载文件的默认名称
		response.setHeader("Content-Disposition",
				"attachment;filename=" + URLEncoder.encode("5.2.3", "UTF-8") + ".xls");
		// 编码
		response.setCharacterEncoding("UTF-8");

		Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), PayMainExcel.class, target);
		workbook.write(response.getOutputStream());
	}

	@Permis
	@Log
	@ApiOperation(value = "员工收款统计汇总", httpMethod = "POST", notes = "返回data参数如下Model说明：", response = QueryPagePayTotalByOperatorRespDTO.class)
	@PostMapping("/queryPagePayTotalByOperator")
	public Object queryPagePayTotalByOperator(HttpServletRequest request, @RequestBody QueryPagePayTotalByOperatorReqDTO req) {
		String operatorId = (String) request.getSession().getAttribute(HttpParamConstant.Session.GL_APP_USER_ID);
		MerchantOperatorDto operator = (MerchantOperatorDto) redisService.get(operatorId);
		if (operator.getLinkType() == _1_) {
			req.setMerchantNo(operator.getParentMerchantNo());
		}
		else {
			req.setMerchantNo(operator.getMerchantNo());
		}
		logger.info("queryPagePayTotalByOperator ===> payqueryService.queryPagePayTotalByOperator req:{}", JsonUtils.toJsonString(req));
		QueryPagePayTotalByOperatorRespDTO respDTO = payQueryRemoteService.queryPagePayTotalByOperator(req);
		logger.info("queryPagePayTotalByOperator ===> payqueryService.queryPagePayTotalByOperator resp:{}", JsonUtils.toJsonString(respDTO));
		String result = respDTO.getResultCode();
		String description = respDTO.getResultDesc();
		if (ResultCodeEnum.System.SUCCESS.getCode().equals(result) && respDTO.getResultData() != null) {
			List<PayTotalByOperatorDTO> payTotalByOperatorDTOs = respDTO.getResultData().getList();
			if (CollectionUtils.isNotEmpty(payTotalByOperatorDTOs)) {
				List<String> userIds = new ArrayList<String>();
				for (PayTotalByOperatorDTO payTotalByOperatorDTO : payTotalByOperatorDTOs) {
					if (StringUtils.isNotBlank(payTotalByOperatorDTO.getOperator())) {
						userIds.add(payTotalByOperatorDTO.getOperator());
					}
					else {
						payTotalByOperatorDTO.setOperatorName("商家");
					}
				}
				if (CollectionUtils.isNotEmpty(userIds)) {
					List<UserUpdateDto> dtos = memberRemoteService.getUserByuserIdList(userIds);
					Map<String, String> userMaps = dtos.stream().collect(Collectors.toMap(UserUpdateDto::getUserId, UserUpdateDto::getRealName));
					for (PayTotalByOperatorDTO payTotalByOperatorDTO : payTotalByOperatorDTOs) {
						String key = payTotalByOperatorDTO.getOperator();
						if (userMaps.get(key) != null) {
							payTotalByOperatorDTO.setOperatorName(userMaps.get(key));
						}
						else {
							payTotalByOperatorDTO.setOperatorName("商家");
						}
					}
				}
			}
			respDTO.getResultData().setList(payTotalByOperatorDTOs);
		}
		respDTO.setResultCode(null);
		respDTO.setResultDesc(null);
		return ResultDto.result(result, description, respDTO);
	}

	@Permis
	@Log
	@ApiOperation(value = "员工收款统计详情", httpMethod = "POST", notes = "返回data参数如下Model说明：", response = QueryPagePayTotalByOperatorRespDTO.class)
	@PostMapping("/queryPagePayTotalByOperatorDetail")
	public Object queryPagePayTotalByOperatorDetail(HttpServletRequest request, @RequestBody QueryPagePayTotalByOperatorDetailReqDTO req) {
		String operatorId = (String) request.getSession().getAttribute(HttpParamConstant.Session.GL_APP_USER_ID);
		MerchantOperatorDto operator = (MerchantOperatorDto) redisService.get(operatorId);
		if (operator.getLinkType() == _1_) {
			req.setMerchantNo(operator.getParentMerchantNo());
		}
		else {
			req.setMerchantNo(operator.getMerchantNo());
		}
		QueryPagePayTotalByOperatorDetailRespDTO respDTO = payQueryRemoteService.queryPagePayTotalByOperatorDetail(req);
		String result = respDTO.getResultCode();
		String description = respDTO.getResultDesc();
		if (ResultCodeEnum.System.SUCCESS.getCode().equals(result) && respDTO.getResultData() != null) {
			List<PayMain> paymains = respDTO.getResultData().getList();
			if (CollectionUtils.isNotEmpty(paymains)) {
				List<String> userIds = new ArrayList<String>();
				for (PayMain payMain : paymains) {
					if (StringUtils.isNotBlank(payMain.getOperator())) {
						userIds.add(payMain.getOperator());
					}
					else {
						payMain.setOperatorName("商家");
					}
				}
				if (CollectionUtils.isNotEmpty(userIds)) {
					List<UserUpdateDto> dtos = memberRemoteService.getUserByuserIdList(userIds);
					Map<String, String> userMaps = dtos.stream().collect(Collectors.toMap(UserUpdateDto::getUserId, UserUpdateDto::getRealName));
					for (PayMain payMain : paymains) {
						String key = payMain.getOperator();
						if (userMaps.get(key) != null) {
							payMain.setOperatorName(userMaps.get(key));
						}
						else {
							payMain.setOperatorName("商家");
						}
					}
				}
			}
			respDTO.getResultData().setList(paymains);
		}
		respDTO.setResultCode(null);
		respDTO.setResultDesc(null);
		return ResultDto.result(result, description, respDTO);
	}

	/**
	 * 撤单
	 * 
	 * @param request
	 * @param req
	 * @return
	 */
	@Permis
	@Log
	@ApiOperation(value = "撤单", httpMethod = "POST", notes = "返回data参数如下Model说明：", response = QueryPagePayTotalByOperatorRespDTO.class)
	@PostMapping("/refund")
	public Object refund(HttpServletRequest request, @RequestBody RefundReq req) {
		String operatorId = (String) request.getSession().getAttribute(HttpParamConstant.Session.GL_APP_USER_ID);
		logger.info("refund begin,operatorId={},req={}", operatorId, req.toString());
		ResultDto<?> resp = null;
		if (null == req || !req.validateParamIsNull("orderId", "payId")) {
			resp = ResultDto.result(ResultCodeEnum.System.PARAM_NULL);
		}
		else {
			try {
				// 1、验证密码
				MerchantOperatorDto operator = (MerchantOperatorDto) redisService.get(operatorId);
				resp = validatePwd(operator, req.getPassword());
				// 2、撤单
				if (null == resp) {
					OrderRefundReq reqParam = new OrderRefundReq();
					reqParam.setOrigOrderSn(req.getOrderId());
					reqParam.setOperatorId(operatorId);
					ResultDto<?> rlt = orderRemoteService.refund(reqParam);
					logger.info("refund ===> orderService.refund rlt:{}", JsonUtils.toJsonString(rlt));
					if (ResultCodeEnum.System.SUCCESS.getCode().equals(rlt.getResult())) {
						resp = ResultDto.result(ResultCodeEnum.System.SUCCESS);
						resp.setDescription("发起撤单成功");
					}
					else if ("008024".equals(rlt.getResult())) {
						resp = ResultDto.result(rlt.getResult(), rlt.getDescription());
					}
					else if ("O80000".equals(rlt.getResult())) {
						resp = ResultDto.result(ResultCodeEnum.Payment.REFUND_ING);
					}
					else {
						resp = ResultDto.result(ResultCodeEnum.Payment.REFUND_FAIL);
					}
				}
			}
			catch (Exception e) {
				logger.error("refund exception, e:" + e);
				resp = ResultDto.errorResult();
			}
		}
		logger.info("refund end,resp:{}", resp.toString());
		return resp;
	}

	private ResultDto<?> validatePwd(MerchantOperatorDto operator, String pwd) {
		logger.info("validatePwd begin,operator={},pwd={}", JsonUtils.toJsonString(operator), pwd);
		ResultDto<?> resp = null;
		try {
			if (StringUtils.isEmpty(pwd)) {
				resp = ResultDto.result(ResultCodeEnum.System.PARAM_NULL);
			}
			if (null == operator) {
				resp = ResultDto.result(ResultCodeEnum.System.NEED_LOGIN);
			}
			else {
				if (PermisConstant.ADMIN.equals(operator.getRoleId())) {
					// 从redis里面验证
					if (!pwd.equals(operator.getPassword())) {
						resp = ResultDto.result(ResultCodeEnum.Settlement.REFUND_PASSWORD_ERRPR);
					}
				}
				else {
					// 调member服务验证
					ValidatePwdReq req = new ValidatePwdReq();
					req.setUserId(operator.getUserId());
					req.setPwd(pwd);
					Boolean rlt = memberRemoteService.validatePassword(req);
					if (rlt == null || !rlt) {
						resp = ResultDto.result(ResultCodeEnum.Settlement.REFUND_PASSWORD_ERRPR);
					}
				}
			}
		}
		catch (Exception e) {
			logger.error("validatePwd exception, e:" + e);
			resp = ResultDto.result(ResultCodeEnum.System.SYSTEM_ERROR);
		}
		return resp;
	}
}
