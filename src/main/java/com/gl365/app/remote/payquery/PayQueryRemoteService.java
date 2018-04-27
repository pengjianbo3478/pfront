package com.gl365.app.remote.payquery;
import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gl365.app.dto.ResultDto;
import com.gl365.app.remote.payquery.request.QueryPagePayMainReqDTO;
import com.gl365.app.remote.payquery.request.QueryPagePayTotalByOperatorDetailReqDTO;
import com.gl365.app.remote.payquery.request.QueryPagePayTotalByOperatorReqDTO;
import com.gl365.app.remote.payquery.response.QueryCollectionDetailRespDTO;
import com.gl365.app.remote.payquery.response.QueryPagePayMainRespDTO;
import com.gl365.app.remote.payquery.response.QueryPagePayMainRespDTOExcel;
import com.gl365.app.remote.payquery.response.QueryPagePayTotalByOperatorDetailRespDTO;
import com.gl365.app.remote.payquery.response.QueryPagePayTotalByOperatorRespDTO;

@FeignClient(name = "payquery", url = "${${env:}.url.payquery:}")
public interface PayQueryRemoteService {
	
	/**
	 * 交易明细列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryPagePayMain", method = RequestMethod.POST)
	public QueryPagePayMainRespDTO queryPagePayMain(@RequestBody QueryPagePayMainReqDTO request);
	

	/**
	 * 交易明细列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryPagePayMain", method = RequestMethod.POST)
	public QueryPagePayMainRespDTOExcel queryPagePayMainExcel(@RequestBody QueryPagePayMainReqDTO request);
	
	
	/**
	 * 收款方式汇总
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryPagePayTotalByOperator", method = RequestMethod.POST)
	public QueryPagePayTotalByOperatorRespDTO queryPagePayTotalByOperator(@RequestBody QueryPagePayTotalByOperatorReqDTO request);
	
	/**
	 * 收款明细查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryPagePayTotalByOperator", method = RequestMethod.POST)
	public ResultDto<List<QueryCollectionDetailRespDTO>> queryPagePayCollectionDetail(@RequestBody QueryPagePayTotalByOperatorReqDTO request);
	

	/**
	 * 收款方式汇总-->详情
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryPagePayTotalByOperatorDetail", method = RequestMethod.POST)
	public QueryPagePayTotalByOperatorDetailRespDTO queryPagePayTotalByOperatorDetail(@RequestBody QueryPagePayTotalByOperatorDetailReqDTO request);
}
