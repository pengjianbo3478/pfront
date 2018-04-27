package com.gl365.app.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl365.app.dto.ResultDto;
import com.gl365.app.enums.ResultCodeEnum;
import com.gl365.app.remote.member.MemberRemoteService;
import com.gl365.app.remote.member.request.UserUpdateDto;
import com.gl365.app.remote.merchant.MerchantRemoteService;
import com.gl365.app.remote.merchant.response.MerchantBasicsInfoDto;
import com.gl365.app.remote.payquery.request.QueryPagePayMainReqDTO;
import com.gl365.app.remote.payquery.request.QueryPagePayTotalByOperatorDetailReqDTO;
import com.gl365.app.remote.payquery.request.QueryPagePayTotalByOperatorReqDTO;
import com.gl365.app.remote.payquery.response.QueryCollectionDetailByOperatorRespDTO;
import com.gl365.app.remote.payquery.response.QueryCollectionDetailRespDTO;
import com.gl365.app.remote.payquery.response.QueryCollectionDetailTotalRespDTO;
import com.gl365.app.remote.payquery.response.QueryPagePayTransStatisTotalRespDTO;
import com.gl365.app.remote.settlequery.SettleQueryRemoteService;
import com.gl365.app.remote.settlequery.request.QueryTransDetailTotalReqDto;
import com.gl365.app.remote.settlequery.request.ResultConvert;
import com.gl365.app.remote.settlequery.response.TransSettlementDetailRespDTO;
import com.gl365.app.service.CollectionService;

@Service
public class CollectionServiceImpl implements CollectionService {
	@Autowired
	private MemberRemoteService memberRemoteService;
	@Autowired
	private MerchantRemoteService merchantRemoteService;
	@Autowired
	private SettleQueryRemoteService settleQueryRemoteService;

	@Override
	public Object queryPagePayCollectionDetail(QueryPagePayTotalByOperatorReqDTO req) {
		ResultDto<List<QueryCollectionDetailRespDTO>> respDTO = settleQueryRemoteService.queryPagePayCollectionDetail(req);
		String code = respDTO.getResult();
		List<QueryCollectionDetailRespDTO> data = respDTO.getData();
		String desc = respDTO.getDescription();
		if (CollectionUtils.isEmpty(data)) return ResultDto.result(code, desc, data);
		List<String> userIdList = new ArrayList<>();
		List<String> merchantIdList = new ArrayList<>();
		this.setQueryList(data, userIdList, merchantIdList);
		Map<String, String> usernameMap = this.getUserMap(userIdList);
		Map<String, String> merchantNameMap = this.getMerchantNameMap(merchantIdList);
		for (QueryCollectionDetailRespDTO item : data) {
			if (item.getOperatorType().equals("1")) {
				item.setOperatorName(usernameMap.get(item.getOperator()));
			}
			else if (item.getOperatorType().equals("2")) {
				item.setOperatorName(merchantNameMap.get(item.getOperator()));
			}
		}
		respDTO.setData(data);
		return ResultConvert.resultConvert(respDTO, data);
	}

	private Map<String, String> getMerchantNameMap(List<String> merchantIdList) {
		if (CollectionUtils.isEmpty(merchantIdList)) return null;
		ResultDto<List<MerchantBasicsInfoDto>> dtos = this.merchantRemoteService.findMerchantInfoByNos(merchantIdList);
		if (CollectionUtils.isEmpty(dtos.getData())) return null;
		Map<String, String> result = new HashMap<String, String>();
		for (MerchantBasicsInfoDto item : dtos.getData()) {
			result.put(item.getMerchantNo(), item.getMerchantName());
		}
		return result;
	}

	private Map<String, String> getUserMap(List<String> userIdList) {
		if (CollectionUtils.isEmpty(userIdList)) return null;
		List<UserUpdateDto> dtos = this.memberRemoteService.getUserByuserIdList(userIdList);
		Map<String, String> result = new HashMap<String, String>();
		if (CollectionUtils.isEmpty(dtos)) return null;
		for (UserUpdateDto item : dtos) {
			result.put(item.getUserId(), item.getRealName());
		}
		return result;
	}

	private void setQueryList(List<QueryCollectionDetailRespDTO> data, List<String> userIdList, List<String> merchantIdList) {
		for (QueryCollectionDetailRespDTO item : data) {
			if (item.getOperatorType().equals("1")) userIdList.add(item.getOperator());
			else if (item.getOperatorType().equals("2")) merchantIdList.add(item.getOperator());
		}
	}

	@Override
	public Object transacStatisDaily(QueryPagePayMainReqDTO req) {
		
		ResultDto<List<TransSettlementDetailRespDTO>> respDTO = this.settleQueryRemoteService.queryPagePayTransDetail(req);
		String result = respDTO.getResult();
		boolean successCode = ResultCodeEnum.System.SUCCESS.getCode().equals(result);
		List<TransSettlementDetailRespDTO> data = respDTO.getData();
		if (!successCode && CollectionUtils.isEmpty(data)) return ResultConvert.resultConvert(respDTO, respDTO.getData());
		List<String> userIdList = new ArrayList<>();
		List<String> merchantIdList = new ArrayList<>();
		this.getsettleDetailIdList(data, userIdList, merchantIdList);
		Map<String, String> usernameMap = this.getUserMap(userIdList);
		Map<String, String> merchantNameMap = this.getMerchantNameMap(merchantIdList);
		for (TransSettlementDetailRespDTO item : data) {
			if (item.getOperatorType().equals("1")) {
				item.setOperatorName(usernameMap.get(item.getOperator()));
			}
			else if (item.getOperatorType().equals("2")) {
				item.setOperatorName(merchantNameMap.get(item.getOperator()));
			}
		}
		respDTO.setData(data);
		return ResultConvert.resultConvert(respDTO, respDTO.getData());
	}
	
	private void getsettleDetailIdList(List<TransSettlementDetailRespDTO> data, List<String> userIdList, List<String> merchantIdList) {
		for (TransSettlementDetailRespDTO item : data) {
			if (item.getOperatorType().equals("1")) userIdList.add(item.getOperator());
			else if (item.getOperatorType().equals("2")) merchantIdList.add(item.getOperator());
		}
	}

	@Override
	public ResultDto<QueryPagePayTransStatisTotalRespDTO> transacStatisCollect(QueryTransDetailTotalReqDto req) {
		ResultDto<QueryPagePayTransStatisTotalRespDTO> respDTO = this.settleQueryRemoteService.queryPagePayTransStatisTotal(req);
		return respDTO;
	}

	@Override
	public Object queryPagePayCollectionDetailTotal(QueryPagePayTotalByOperatorReqDTO req) {
		ResultDto<QueryCollectionDetailTotalRespDTO> respDTO = this.settleQueryRemoteService.queryPagePayCollectionDetailTotal(req);
		return respDTO;
	}

	@Override
	public Object queryPagePayCollectionDetailByOperator(QueryPagePayTotalByOperatorDetailReqDTO req) {
		
		ResultDto<List<QueryCollectionDetailByOperatorRespDTO>> respDTO = this.settleQueryRemoteService.queryPagePayCollectionDetailByOperator(req);
		String result = respDTO.getResult();
		boolean successCode = ResultCodeEnum.System.SUCCESS.getCode().equals(result);
		List<QueryCollectionDetailByOperatorRespDTO> data = respDTO.getData();
		if (!successCode && CollectionUtils.isEmpty(data)) return ResultConvert.resultConvert(respDTO, respDTO.getData());
		List<String> userIdList = new ArrayList<>();
		List<String> merchantIdList = new ArrayList<>();
		this.getCollectionDetailIdList(data, userIdList, merchantIdList);
		Map<String, String> usernameMap = this.getUserMap(userIdList);
		Map<String, String> merchantNameMap = this.getMerchantNameMap(merchantIdList);
		for (QueryCollectionDetailByOperatorRespDTO item : data) {
			if (item.getOperatorType().equals("1")) {
				item.setOperatorName(usernameMap.get(item.getOperator()));
			}
			else if (item.getOperatorType().equals("2")) {
				item.setOperatorName(merchantNameMap.get(item.getOperator()));
			}
		}
		respDTO.setData(data);
		return ResultConvert.resultConvert(respDTO, respDTO.getData());
	}
	
	private void getCollectionDetailIdList(List<QueryCollectionDetailByOperatorRespDTO> data, List<String> userIdList, List<String> merchantIdList) {
		for (QueryCollectionDetailByOperatorRespDTO item : data) {
			if (item.getOperatorType().equals("1")) userIdList.add(item.getOperator());
			else if (item.getOperatorType().equals("2")) merchantIdList.add(item.getOperator());
		}
	}

	@Override
	public Object querySettlementProfitDetailTotal(QueryPagePayMainReqDTO req) {
		ResultDto<List<TransSettlementDetailRespDTO>> respDTO = this.settleQueryRemoteService.pcQueryPageTransSettleDetail(req);
		String result = respDTO.getResult();
		boolean successCode = ResultCodeEnum.System.SUCCESS.getCode().equals(result);
		List<TransSettlementDetailRespDTO> data = respDTO.getData();
		if (!successCode && CollectionUtils.isEmpty(data)) return ResultConvert.resultConvert(respDTO, respDTO.getData());
		List<String> userIdList = new ArrayList<>();
		List<String> merchantIdList = new ArrayList<>();
		this.getsettleDetailIdList(data, userIdList, merchantIdList);
		Map<String, String> usernameMap = this.getUserMap(userIdList);
		Map<String, String> merchantNameMap = this.getMerchantNameMap(merchantIdList);
		for (TransSettlementDetailRespDTO item : data) {
			if (item.getOperatorType().equals("1")) {
				item.setOperatorName(usernameMap.get(item.getOperator()));
			}
			else if (item.getOperatorType().equals("2")) {
				item.setOperatorName(merchantNameMap.get(item.getOperator()));
			}
		}
		respDTO.setData(data);
		return ResultConvert.resultConvert(respDTO, respDTO.getData());
	}
}
