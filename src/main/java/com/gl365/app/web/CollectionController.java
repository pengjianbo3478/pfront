package com.gl365.app.web;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.app.common.HttpParamConstant;
import com.gl365.app.common.Log;
import com.gl365.app.common.Permis;
import com.gl365.app.dto.ResultDto;
import com.gl365.app.remote.merchant.response.MerchantOperatorDto;
import com.gl365.app.remote.payquery.request.QueryPagePayMainReqDTO;
import com.gl365.app.remote.payquery.request.QueryPagePayTotalByOperatorDetailReqDTO;
import com.gl365.app.remote.payquery.request.QueryPagePayTotalByOperatorReqDTO;
import com.gl365.app.remote.payquery.response.QueryCollectionDetailByOperatorRespDTO;
import com.gl365.app.remote.payquery.response.QueryCollectionDetailRespDTO;
import com.gl365.app.remote.payquery.response.QueryCollectionDetailTotalRespDTO;
import com.gl365.app.remote.payquery.response.QueryPagePayTransStatisTotalRespDTO;
import com.gl365.app.remote.settlequery.request.QueryTransDetailTotalReqDto;
import com.gl365.app.remote.settlequery.response.TransSettlementDetailRespDTO;
import com.gl365.app.service.CollectionService;
import com.gl365.app.service.RedisService;
import com.gl365.app.utils.GsonUtils;
import com.gl365.app.utils.JsonUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins = "*", maxAge = 3600) // 跨域问题，与前端调试时打开
@Api(description = "交易收款明细相关接口--v1.2.1新接口")
@RestController
@RequestMapping("/businessAPI/settlement")
public class CollectionController {
	private static final Logger logger = LoggerFactory.getLogger(CollectionController.class);
	private static final int _1_ = 1;
	@Resource
	private RedisService redisService;
	@Autowired
	CollectionService collectionService;

	@Permis
	@Log
	@ApiOperation(value = "10.8.1交易统计-交易明细查询", httpMethod = "POST", notes = "返回data参数如下Model说明：", response = TransSettlementDetailRespDTO.class)
	@PostMapping("/queryPagePayTransDetail")
	public Object transacStatisDaily(HttpServletRequest request, @RequestBody QueryPagePayMainReqDTO req) {
		MerchantOperatorDto operator = getMerchantOperator(request);
		// if (operator.getLinkType() == _1_) {
		// req.setMerchantNo(operator.getParentMerchantNo());
		// } else {
		req.setMerchantNo(operator.getMerchantNo());
		// }
		logger.info("req:{}", JsonUtils.toJsonString(req));
		Object result = this.collectionService.transacStatisDaily(req);
		logger.info("reps:{}", JsonUtils.toJsonString(result));
		return result;
	}

	@Permis
	@Log
	@ApiOperation(value = "10.8.2交易统计-交易明细汇总查询", httpMethod = "POST", notes = "返回data参数如下Model说明：", response = QueryPagePayTransStatisTotalRespDTO.class)
	@PostMapping("/queryPagePayTransDetailTotal")
	public ResultDto<QueryPagePayTransStatisTotalRespDTO> transacStatisCollect(HttpServletRequest request, @RequestBody QueryTransDetailTotalReqDto req) {
		MerchantOperatorDto operator = getMerchantOperator(request);
//		 if (operator.getLinkType() == _1_) {
//		 req.setMerchantNo(operator.getParentMerchantNo());
//		 } else {
		req.setMerchantNo(operator.getMerchantNo());
//		 }
		logger.info("req:{}", GsonUtils.toJson(req));
		ResultDto<QueryPagePayTransStatisTotalRespDTO> result = this.collectionService.transacStatisCollect(req);
		logger.info("resp:{}", GsonUtils.toJson(result));
		return result;
	}

	@Permis
	@Log
	@ApiOperation(value = "10.9.1收款统计-收款明细查询", httpMethod = "POST", notes = "返回data参数如下Model说明：", response = QueryCollectionDetailRespDTO.class)
	@PostMapping("/queryPagePayCollectionDetail")
	public Object queryPagePayCollectionDetail(HttpServletRequest request, @RequestBody QueryPagePayTotalByOperatorReqDTO req) {
		MerchantOperatorDto operator = getMerchantOperator(request);
		if (operator.getLinkType() == _1_) req.setMerchantNo(operator.getParentMerchantNo());
		else req.setMerchantNo(operator.getMerchantNo());
		logger.info("req={}", JsonUtils.toJsonString(req));
		Object result = this.collectionService.queryPagePayCollectionDetail(req);
		return result;
	}

	@Permis
	@Log
	@ApiOperation(value = "10.9.2收款统计-收款明细汇总查询", httpMethod = "POST", notes = "返回data参数如下Model说明：", response = QueryCollectionDetailTotalRespDTO.class)
	@PostMapping("/queryPagePayCollectionDetailTotal")
	public Object queryPagePayCollectionDetailTotal(HttpServletRequest request, @RequestBody QueryPagePayTotalByOperatorReqDTO req) {
		MerchantOperatorDto operator = getMerchantOperator(request);
		if (operator.getLinkType() == _1_) req.setMerchantNo(operator.getParentMerchantNo());
		else req.setMerchantNo(operator.getMerchantNo());
		logger.info("req:{}", JsonUtils.toJsonString(req));
		Object result = this.collectionService.queryPagePayCollectionDetailTotal(req);
		logger.info("resp:{}", JsonUtils.toJsonString(result));
		return result;
	}

	@Permis
	@Log
	@ApiOperation(value = "10.9.3收款统计-收款人收款明细查询", httpMethod = "POST", notes = "返回data参数如下Model说明：", response = QueryCollectionDetailByOperatorRespDTO.class)
	@PostMapping("/queryPagePayCollectionDetailByOperator")
	public Object queryPagePayCollectionDetailByOperator(HttpServletRequest request, @RequestBody QueryPagePayTotalByOperatorDetailReqDTO req) {
		MerchantOperatorDto operator = getMerchantOperator(request);
		if (operator.getLinkType() == _1_) req.setMerchantNo(operator.getParentMerchantNo());
		else req.setMerchantNo(operator.getMerchantNo());
		logger.info("req:{}", JsonUtils.toJsonString(req));
		Object result = this.collectionService.queryPagePayCollectionDetailByOperator(req);
		logger.info("resp:{}", JsonUtils.toJsonString(result));
		return result;
	}

	@Permis
	@Log
	@ApiOperation(value = "10.4.4交易统计-清算明细查询", httpMethod = "POST", notes = "返回data参数如下Model说明：", response = TransSettlementDetailRespDTO.class)
	@PostMapping(value = "queryPageTransSettlementDetail")
	public Object querySettlementProfitDetailTotal(HttpServletRequest request, @RequestBody QueryPagePayMainReqDTO req) {
		MerchantOperatorDto operator = getMerchantOperator(request);
		// if (PermisConstant.ADMIN.equals(operator.getRoleId())) {
		if (operator.getLinkType() == _1_) req.setMerchantNo(operator.getParentMerchantNo());
		else req.setMerchantNo(operator.getMerchantNo());
		logger.debug("req:{}", JsonUtils.toJsonString(req));
		Object result = this.collectionService.querySettlementProfitDetailTotal(req);
		logger.debug("resp:{}", JsonUtils.toJsonString(result));
		return result;
	}

	private MerchantOperatorDto getMerchantOperator(HttpServletRequest request) {
		String operatorId = (String) request.getSession().getAttribute(HttpParamConstant.Session.GL_APP_USER_ID);
		return (MerchantOperatorDto) redisService.get(operatorId);
	}
}
