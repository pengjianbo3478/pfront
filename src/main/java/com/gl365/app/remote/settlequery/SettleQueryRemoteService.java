package com.gl365.app.remote.settlequery;
import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gl365.app.dto.ResultDto;
import com.gl365.app.remote.payquery.request.QueryPagePayMainReqDTO;
import com.gl365.app.remote.payquery.request.QueryPagePayTotalByOperatorDetailReqDTO;
import com.gl365.app.remote.payquery.request.QueryPagePayTotalByOperatorReqDTO;
import com.gl365.app.remote.payquery.response.QueryCollectionDetailByOperatorRespDTO;
import com.gl365.app.remote.payquery.response.QueryCollectionDetailRespDTO;
import com.gl365.app.remote.payquery.response.QueryCollectionDetailTotalRespDTO;
import com.gl365.app.remote.payquery.response.QueryPagePayTransStatisTotalRespDTO;
import com.gl365.app.remote.settlequery.request.HomeSurveyTodayEaringsReqDto;
import com.gl365.app.remote.settlequery.request.MerchantSettlementForPC;
import com.gl365.app.remote.settlequery.request.MerchantSettlementForPCReq;
import com.gl365.app.remote.settlequery.request.MerchantSettlementSumForPC;
import com.gl365.app.remote.settlequery.request.OwnerReq;
import com.gl365.app.remote.settlequery.request.ProfitDetailedReq;
import com.gl365.app.remote.settlequery.request.QueryTransDetailTotalReqDto;
import com.gl365.app.remote.settlequery.request.SettlementProfitDetailedReq;
import com.gl365.app.remote.settlequery.request.TransacStatisReq;
import com.gl365.app.remote.settlequery.response.HomeSurveyTotalEarningsRespDto;
import com.gl365.app.remote.settlequery.response.ProfitDetailedResp;
import com.gl365.app.remote.settlequery.response.ProfitDetailedSumResp;
import com.gl365.app.remote.settlequery.response.ProfitStatisDailyResp;
import com.gl365.app.remote.settlequery.response.ProfitStatisMonthResp;
import com.gl365.app.remote.settlequery.response.SettlementProfitDetailedResp;
import com.gl365.app.remote.settlequery.response.SettlementProfitDetailedSumResp;
import com.gl365.app.remote.settlequery.response.TransSettlementDetailRespDTO;
import com.gl365.app.remote.settlequery.response.TransacStatisDailyResp;
import com.gl365.app.remote.settlequery.response.TransacStatisMonthResp;

import io.swagger.annotations.ApiOperation;
@FeignClient(name = "settlequery")
public interface SettleQueryRemoteService {
	/**
	 * 10.1.1首页交易统计-按日统计
	 * 
	 * @param reqParam
	 * @return
	 */
	@RequestMapping(value = "/payMain/statsPayMainByDate", method = RequestMethod.POST)
	ResultDto<List<TransacStatisDailyResp>> TransacStatisDaily(@RequestBody TransacStatisReq reqParam);

	/**
	 * 10.1.2首页交易统计-按月统计
	 * 
	 * @param reqParam
	 * @return
	 */
	@RequestMapping(value = "/payMain/statsPayMainByMonth", method = RequestMethod.POST)
	ResultDto<List<TransacStatisMonthResp>> TransacStatisMonth(@RequestBody TransacStatisReq reqParam);

	/**
	 * 10.2.1首页收益统计-按日统计
	 * 
	 * @param reqParam
	 * @return
	 */
	@RequestMapping(value = "/payProfit/statsPayProfitByDate", method = RequestMethod.POST)
	ResultDto<List<ProfitStatisDailyResp>> ProfitStatisDaily(@RequestBody OwnerReq reqParam);

	/**
	 * 10.2.1首页收益统计-按月统计
	 * 
	 * @param reqParam
	 * @return
	 */
	@RequestMapping(value = "/payProfit/statsPayProfitByMonth", method = RequestMethod.POST)
	ResultDto<List<ProfitStatisMonthResp>> ProfitStatisMonth(@RequestBody OwnerReq reqParam);
	
	/**
	 * 10.5收益明细-收益明细查询
	 * @param reqParam
	 * @return
	 */
	@RequestMapping(value = "/payProfit/queryPage/queryPayProfitDetailForM_V105", method = RequestMethod.POST)
	ResultDto<List<ProfitDetailedResp>> queryProfitDetailed(@RequestBody ProfitDetailedReq reqParam);

	/**
	 * 10.6收益明细-分润统计查询
	 * @param reqParam
	 * @return
	 */
	@RequestMapping(value = "/payProfit/queryPayProfitDetailSumForM_V106", method = RequestMethod.POST)
	ResultDto<ProfitDetailedSumResp> queryProfitDetailedSum(@RequestBody ProfitDetailedReq reqParam);

	/**
	 * 10.7.1收益结算-明细查询
	 * @param reqParam
	 * @return
	 */
	@RequestMapping(value = "/vcCashRequest/queryPage/queryVcCashRequestList", method = RequestMethod.POST)
	ResultDto<List<SettlementProfitDetailedResp>> querySettlementProfitDetailed(@RequestBody SettlementProfitDetailedReq reqParam);

	/**
	 * 10.7.2收益结算-合计查询
	 * @param reqParam
	 * @return
	 */
	@RequestMapping(value = "/vcCashRequest/queryVcCashRequestListSum", method = RequestMethod.POST)
	ResultDto<SettlementProfitDetailedSumResp> querySettlementProfitDetailedSum(@RequestBody SettlementProfitDetailedReq reqParam);

	@ApiOperation(value = "10.4.3交易结算-已结算统计明细", notes = "10.4.1交易结算-已结算统计明细")
	@RequestMapping(value = "/settlementMergeTxn/pc/queryPage", method = RequestMethod.POST)
	public ResultDto<List<MerchantSettlementForPC>> pcQueryPage(@RequestBody MerchantSettlementForPCReq req);

	@ApiOperation(value = "10.4.2交易结算-已结算统计合计", notes = "10.4.2交易结算-已结算统计合计")
	@RequestMapping(value = "/merchantSettle/pc/queryPageSum", method = RequestMethod.POST)
	public ResultDto<MerchantSettlementSumForPC> pcQueryPageSum(@RequestBody MerchantSettlementForPCReq req);
	
	@ApiOperation(value = "10.4.4交易统计-清算明细查询", notes = "10.4.4交易统计-清算明细查询")
	@RequestMapping(value = "/settlementMergeTxn/pc/queryPageDetail", method = RequestMethod.POST)
	public ResultDto<List<TransSettlementDetailRespDTO>> pcQueryPageTransSettleDetail(@RequestBody QueryPagePayMainReqDTO req);
	
	/**
	 * 交易明细列表（v1.2.1）
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/payMain/queryPayDetailForPC1081", method = RequestMethod.POST)
	public ResultDto<List<TransSettlementDetailRespDTO>> queryPagePayTransDetail(@RequestBody QueryPagePayMainReqDTO request);
	
	/**
	 * 交易明细汇总
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/payMain/sumPayDetailForPC1082", method = RequestMethod.POST)
	public ResultDto<QueryPagePayTransStatisTotalRespDTO> queryPagePayTransStatisTotal(@RequestBody QueryTransDetailTotalReqDto request);
	
	/**
	 * 收款明细查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/reportReceipt/queryPage", method = RequestMethod.POST)
	public ResultDto<List<QueryCollectionDetailRespDTO>> queryPagePayCollectionDetail(@RequestBody QueryPagePayTotalByOperatorReqDTO request);
	
	/**
	 * 收款明细汇总查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/reportReceipt/queryPageSum", method = RequestMethod.POST)
	public ResultDto<QueryCollectionDetailTotalRespDTO> queryPagePayCollectionDetailTotal(@RequestBody QueryPagePayTotalByOperatorReqDTO request);
	
	/**
	 * 收款人收款明细查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/reportReceipt/queryPageDetail", method = RequestMethod.POST)
	public ResultDto<List<QueryCollectionDetailByOperatorRespDTO>> queryPagePayCollectionDetailByOperator(@RequestBody QueryPagePayTotalByOperatorDetailReqDTO request);
	
	/**
	 * 首页推广总览总收益查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/profitAgentSum/queryProfitAgentSumByIds", method = RequestMethod.POST)
	public ResultDto<List<HomeSurveyTotalEarningsRespDto>> queryTotleEarnings(@RequestBody HomeSurveyTodayEaringsReqDto request);
	
	/**
	 * 首页推广总览今昨日收益查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/profitAgentDaily/query", method = RequestMethod.POST)
	public ResultDto<List<HomeSurveyTotalEarningsRespDto>> queryTodayEarnings(@RequestBody HomeSurveyTodayEaringsReqDto request);
}
