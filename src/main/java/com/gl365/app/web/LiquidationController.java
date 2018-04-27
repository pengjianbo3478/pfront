package com.gl365.app.web;
import java.util.List;
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
import com.gl365.app.common.PermisConstant;
import com.gl365.app.dto.ResultDto;
import com.gl365.app.enums.ResultCodeEnum;
import com.gl365.app.remote.account.AccountRemoteService;
import com.gl365.app.remote.account.request.QueryProfitAccountReq;
import com.gl365.app.remote.account.response.QueryProfitAccountResp;
import com.gl365.app.remote.merchant.response.MerchantOperatorDto;
import com.gl365.app.remote.settlequery.SettleQueryRemoteService;
import com.gl365.app.remote.settlequery.request.MerchantSettlementForPC;
import com.gl365.app.remote.settlequery.request.MerchantSettlementForPCReq;
import com.gl365.app.remote.settlequery.request.MerchantSettlementSumForPC;
import com.gl365.app.remote.settlequery.request.ProfitDetailedReq;
import com.gl365.app.remote.settlequery.request.ResultConvert;
import com.gl365.app.remote.settlequery.request.SettlementProfitDetailedReq;
import com.gl365.app.remote.settlequery.response.ProfitDetailedResp;
import com.gl365.app.remote.settlequery.response.ProfitDetailedSumResp;
import com.gl365.app.remote.settlequery.response.SettlementProfitDetailedResp;
import com.gl365.app.remote.settlequery.response.SettlementProfitDetailedSumResp;
import com.gl365.app.service.RedisService;
import com.gl365.app.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins = "*", maxAge = 3600) // 跨域问题，与前端调试时打开
@Api(description = "清算相关接口")
@RestController
@RequestMapping("/businessAPI/liquidation")
public class LiquidationController {
	private static final Logger logger = LoggerFactory.getLogger(LiquidationController.class);
	private static final int _1_ = 1;
	private static final String _6 = "6";
	private static final String _5 = "5";
	@Autowired
	private SettleQueryRemoteService settleQueryRemoteService;
	@Autowired
	private RedisService redisService;
	@Autowired
	private AccountRemoteService accountRemoteService;

	@Log
	@Permis
	@ApiOperation("10.5收益明细-收益明细查询")
	@PostMapping("/queryProfitDetailed")
	public Object queryProfitDetailed(HttpServletRequest request, @RequestBody ProfitDetailedReq req) {
		MerchantOperatorDto operator = getMerchantOperator(request);
		if (PermisConstant.ADMIN.equals(operator.getRoleId())) {
			req.setOwnerType(_5);
			if (operator.getLinkType() == _1_) {
				req.setOwnerId(operator.getParentMerchantNo());
			}
			else {
				req.setOwnerId(operator.getMerchantNo());
			}
		}
		else {
			req.setOwnerId(operator.getUserId());
			req.setOwnerType(_6);
		}
		ResultDto<List<ProfitDetailedResp>> rlt = settleQueryRemoteService.queryProfitDetailed(req);
		return ResultConvert.resultConvert(rlt, rlt.getData());
	}

	@Log
	@Permis
	@ApiOperation("10.6收益明细-分润统计查询")
	@PostMapping("/queryProfitDetailedSum")
	public ResultDto<ProfitDetailedSumResp> queryProfitDetailedSum(HttpServletRequest request, @RequestBody ProfitDetailedReq req) {
		MerchantOperatorDto operator = getMerchantOperator(request);
		if (PermisConstant.ADMIN.equals(operator.getRoleId())) {
			req.setOwnerType(_5);
			if (operator.getLinkType() == _1_) {
				req.setOwnerId(operator.getParentMerchantNo());
			}
			else {
				req.setOwnerId(operator.getMerchantNo());
			}
		}
		else {
			req.setOwnerId(operator.getUserId());
			req.setOwnerType(_6);
		}
		return settleQueryRemoteService.queryProfitDetailedSum(req);
	}

	@Log
	@Permis
	@ApiOperation("10.7.1收益结算-明细查询")
	@PostMapping("/querySettlementProfitDetailed")
	public Object querySettlementProfitDetailed(HttpServletRequest request, @RequestBody SettlementProfitDetailedReq req) {
		MerchantOperatorDto operator = getMerchantOperator(request);
		if (PermisConstant.ADMIN.equals(operator.getRoleId())) {
			req.setOwnerType(_5);
			if (operator.getLinkType() == _1_) {
				req.setOwnerId(operator.getParentMerchantNo());
			}
			else {
				req.setOwnerId(operator.getMerchantNo());
			}
		}
		else {
			req.setOwnerId(operator.getUserId());
			req.setOwnerType(_6);
		}
		logger.info("queryPagePayMain ===> liquidationService.querySettlementProfitDetailed req:{}", JsonUtils.toJsonString(req));
		ResultDto<List<SettlementProfitDetailedResp>> rlt = settleQueryRemoteService.querySettlementProfitDetailed(req);
		logger.info("queryPagePayMain ===> liquidationService.querySettlementProfitDetailed resp:{}", JsonUtils.toJsonString(rlt));
		return ResultConvert.resultConvert(rlt, rlt.getData());
	}

	@Log
	@Permis
	@ApiOperation("10.7.2收益结算-合计查询")
	@PostMapping("/querySettlementProfitDetailedSum")
	public ResultDto<SettlementProfitDetailedSumResp> querySettlementProfitDetailedSum(HttpServletRequest request, @RequestBody SettlementProfitDetailedReq req) {
		MerchantOperatorDto operator = getMerchantOperator(request);
		if (PermisConstant.ADMIN.equals(operator.getRoleId())) {
			req.setOwnerType(_5);
			if (operator.getLinkType() == _1_) {
				req.setOwnerId(operator.getParentMerchantNo());
			}
			else {
				req.setOwnerId(operator.getMerchantNo());
			}
		}
		else {
			req.setOwnerId(operator.getUserId());
			req.setOwnerType(_6);
		}
		return settleQueryRemoteService.querySettlementProfitDetailedSum(req);
	}

	@Log
	@Permis
	@ApiOperation("10.4.3交易结算-已结算统计明细")
	@PostMapping("/pc/queryPage")
	public Object querySettlementProfitDetailed(HttpServletRequest request, @RequestBody MerchantSettlementForPCReq req) {
		MerchantOperatorDto operator = getMerchantOperator(request);
		// if (PermisConstant.ADMIN.equals(operator.getRoleId())) {
		if (operator.getLinkType() == _1_) {
			req.setSettleMerchantNo(operator.getParentMerchantNo());
		}
		else {
			req.setSettleMerchantNo(operator.getMerchantNo());
		}
		// }
		logger.info("queryPagePayMain ===> liquidationService.pcQueryPage req:{}", JsonUtils.toJsonString(req));
		ResultDto<List<MerchantSettlementForPC>> rlt = settleQueryRemoteService.pcQueryPage(req);
		logger.info("queryPagePayMain ===> liquidationService.pcQueryPage resp:{}", JsonUtils.toJsonString(rlt));
		return ResultConvert.resultConvert(rlt, rlt.getData());
	}

	@Log
	@Permis
	@ApiOperation("10.4.2交易结算-已结算统计合计")
	@PostMapping("/pc/queryPageSum")
	public ResultDto<MerchantSettlementSumForPC> queryProfitDetailedSum(HttpServletRequest request, @RequestBody MerchantSettlementForPCReq req) {
		MerchantOperatorDto operator = getMerchantOperator(request);
		// if (PermisConstant.ADMIN.equals(operator.getRoleId())) {
		if (operator.getLinkType() == _1_) {
			req.setSettleMerchantNo(operator.getParentMerchantNo());
		}
		else {
			req.setSettleMerchantNo(operator.getMerchantNo());
		}
		// }
		return settleQueryRemoteService.pcQueryPageSum(req);
	}

	@Log
	@Permis
	@ApiOperation(value = "查询余额账户", response = QueryProfitAccountResp.class)
	@PostMapping("/pc/profitAccount/queryProfitAccount")
	public ResultDto<QueryProfitAccountResp> queryProfitAccount(HttpServletRequest request) {
		QueryProfitAccountReq queryProfitAccountReq = new QueryProfitAccountReq();
		MerchantOperatorDto operator = getMerchantOperator(request);
		if (PermisConstant.ADMIN.equals(operator.getRoleId())) {
			if (operator.getLinkType() == _1_) {
				queryProfitAccountReq.setAccountType(_5);
				queryProfitAccountReq.setAccountId(operator.getParentMerchantNo());
			}
			else {
				queryProfitAccountReq.setAccountType(_5);
				queryProfitAccountReq.setAccountId(operator.getMerchantNo());
			}
		}
		else {
			queryProfitAccountReq.setAccountType(_6);
			queryProfitAccountReq.setAccountId(operator.getUserId());
		}
		return accountRemoteService.queryProfitAccount(queryProfitAccountReq);
	}

	private MerchantOperatorDto getMerchantOperator(HttpServletRequest request) {
		String operatorId = (String) request.getSession().getAttribute(HttpParamConstant.Session.GL_APP_USER_ID);
		return (MerchantOperatorDto) redisService.get(operatorId);
	}

	@Deprecated
	@ApiOperation(value = "模拟登录接口,只生成USERID，不考虑其他业务逻辑")
	@PostMapping("/user/loginByManual")
	public Object loginByManual(HttpServletRequest request, @RequestBody MerchantOperatorDto operator) {
		ResultDto<Integer> response = new ResultDto<>();
		request.getSession().setAttribute(HttpParamConstant.Session.GL_APP_USER_ID, operator.getOperatorId());
		response.setResult(ResultCodeEnum.System.SUCCESS.getCode());
		redisService.set(operator.getOperatorId(), operator, 86400L);
		return response;
	}
}
