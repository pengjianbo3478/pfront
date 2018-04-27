package com.gl365.app.web;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl365.app.common.HttpParamConstant;
import com.gl365.app.common.Log;
import com.gl365.app.common.Permis;
import com.gl365.app.dto.ResultDto;
import com.gl365.app.enums.ResultCodeEnum;
import com.gl365.app.remote.merchant.response.MerchantOperatorDto;
import com.gl365.app.remote.settlequery.SettleQueryRemoteService;
import com.gl365.app.remote.settlequery.request.HomeSurveyTodayEaringsReqDto;
import com.gl365.app.remote.settlequery.request.OwnerReq;
import com.gl365.app.remote.settlequery.request.TransacStatisReq;
import com.gl365.app.remote.settlequery.response.HomeSurveyTotalEarningsRespDto;
import com.gl365.app.remote.settlequery.response.ProfitStatisDailyResp;
import com.gl365.app.remote.settlequery.response.ProfitStatisMonthResp;
import com.gl365.app.remote.settlequery.response.TransacStatisDailyResp;
import com.gl365.app.remote.settlequery.response.TransacStatisMonthResp;
import com.gl365.app.service.RedisService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins = "*", maxAge = 3600) // 跨域问题，与前端调试时打开
@Api(description = "首页相关接口")
@RestController
@RequestMapping("/businessAPI/homePage")
public class HomePageController {
	private static final int _1_ = 1;
	private static final String _6 = "6";
	private static final String _5 = "5";
	private static final String _1 = "1";
	@Resource
	private SettleQueryRemoteService settleQueryRemoteService;
	@Resource
	private RedisService redisService;

	@Permis
	@Log
	@ApiOperation(value = "10.1.1首页交易统计-按日统计", httpMethod = "POST", notes = "返回data参数如下Model说明：", response = TransacStatisDailyResp.class)
	@PostMapping("/home/transacStatisDaily")
	public Object transacStatisDaily(HttpServletRequest request, @RequestBody TransacStatisReq req) {
		String operatorId = (String) request.getSession().getAttribute(HttpParamConstant.Session.GL_APP_USER_ID);
		MerchantOperatorDto operator = (MerchantOperatorDto) redisService.get(operatorId);
		if (operator.getLinkType() == _1_) {
			req.setMerchantNo(operator.getParentMerchantNo());
		}
		else {
			req.setMerchantNo(operator.getMerchantNo());
		}
		return settleQueryRemoteService.TransacStatisDaily(req);
	}

	@Permis
	@Log
	@ApiOperation(value = "10.1.2首页交易统计-按月统计", httpMethod = "POST", notes = "返回data参数如下Model说明：", response = TransacStatisMonthResp.class)
	@PostMapping("/home/transacStatisMonth")
	public Object transacStatisMonth(HttpServletRequest request, @RequestBody TransacStatisReq req) {
		String operatorId = (String) request.getSession().getAttribute(HttpParamConstant.Session.GL_APP_USER_ID);
		MerchantOperatorDto operator = (MerchantOperatorDto) redisService.get(operatorId);
		if (operator.getLinkType() == _1_) {
			req.setMerchantNo(operator.getParentMerchantNo());
		}
		else {
			req.setMerchantNo(operator.getMerchantNo());
		}
		return settleQueryRemoteService.TransacStatisMonth(req);
	}

	@Permis
	@Log
	@ApiOperation(value = "10.2.1首页收益统计-按日统计", httpMethod = "POST", notes = "返回data参数如下Model说明：", response = ProfitStatisDailyResp.class)
	@PostMapping("/home/profitStatisDaily")
	public Object profitStatisDaily(HttpServletRequest request, @RequestBody OwnerReq req) {
		String operatorId = (String) request.getSession().getAttribute(HttpParamConstant.Session.GL_APP_USER_ID);
		MerchantOperatorDto operator = (MerchantOperatorDto) redisService.get(operatorId);
		if (_1.equals(operator.getRoleId())) {
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
		return settleQueryRemoteService.ProfitStatisDaily(req);
	}

	@Permis
	@Log
	@ApiOperation(value = "10.2.1首页收益统计-按月统计", httpMethod = "POST", notes = "返回data参数如下Model说明：", response = ProfitStatisMonthResp.class)
	@PostMapping("/home/profitStatisMonth")
	public Object profitStatisMonth(HttpServletRequest request, @RequestBody OwnerReq req) {
		String operatorId = (String) request.getSession().getAttribute(HttpParamConstant.Session.GL_APP_USER_ID);
		MerchantOperatorDto operator = (MerchantOperatorDto) redisService.get(operatorId);
		if (_1.equals(operator.getRoleId())) {
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
		return settleQueryRemoteService.ProfitStatisMonth(req);
	}
	
	@Permis
	@Log
	@ApiOperation(value = "推广总览-累计收益", httpMethod = "POST", notes = "返回data参数如下Model说明：", response = HomeSurveyTotalEarningsRespDto.class) 
	public Object queryTotleEarnings(HttpServletRequest request,@RequestBody HomeSurveyTodayEaringsReqDto req) {
		
		ResultDto<List<HomeSurveyTotalEarningsRespDto>> todayResult = this.settleQueryRemoteService.queryTodayEarnings(req);
		ResultDto<List<HomeSurveyTotalEarningsRespDto>> totalResult = this.settleQueryRemoteService.queryTotleEarnings(req);
		String resultCode = todayResult.getResult();
		String restltDes = todayResult.getDescription();
		List<HomeSurveyTotalEarningsRespDto> todayRespData = todayResult.getData();
		List<HomeSurveyTotalEarningsRespDto> totalRespData = totalResult.getData();
		boolean successCode = ResultCodeEnum.System.SUCCESS.getCode().equals(resultCode);
		if(!successCode && CollectionUtils.isEmpty(todayRespData)) return ResultDto.result(resultCode, restltDes, todayRespData);
		if(!successCode && CollectionUtils.isEmpty(totalRespData)) return ResultDto.result(resultCode, restltDes, totalRespData);
		Map<String,List<HomeSurveyTotalEarningsRespDto>> mapData = new HashMap<String,List<HomeSurveyTotalEarningsRespDto>>();
		mapData.put("todayEarningsData", todayRespData);
		mapData.put("totalEarningsData", totalRespData);
		return ResultDto.result(resultCode, restltDes, mapData);
	}
}
