package com.gl365.app.service;

import com.gl365.app.dto.ResultDto;
import com.gl365.app.remote.payquery.request.QueryPagePayMainReqDTO;
import com.gl365.app.remote.payquery.request.QueryPagePayTotalByOperatorDetailReqDTO;
import com.gl365.app.remote.payquery.request.QueryPagePayTotalByOperatorReqDTO;
import com.gl365.app.remote.payquery.response.QueryPagePayTransStatisTotalRespDTO;
import com.gl365.app.remote.settlequery.request.QueryTransDetailTotalReqDto;

public interface CollectionService {
	
	Object queryPagePayCollectionDetail(QueryPagePayTotalByOperatorReqDTO req);
	Object transacStatisDaily(QueryPagePayMainReqDTO req);
	ResultDto<QueryPagePayTransStatisTotalRespDTO> transacStatisCollect(QueryTransDetailTotalReqDto req);
	Object queryPagePayCollectionDetailTotal(QueryPagePayTotalByOperatorReqDTO req);
	Object queryPagePayCollectionDetailByOperator(QueryPagePayTotalByOperatorDetailReqDTO req);
	Object querySettlementProfitDetailTotal(QueryPagePayMainReqDTO req);
}
