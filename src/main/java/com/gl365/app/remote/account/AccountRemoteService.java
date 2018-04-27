package com.gl365.app.remote.account;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gl365.app.dto.ResultDto;
import com.gl365.app.remote.account.request.QueryProfitAccountReq;
import com.gl365.app.remote.account.response.QueryProfitAccountResp;
/**
 * 账户系统服务
 * 
 * @author dfs_519 2017年4月24日下午6:27:53
 */
@FeignClient(name = "account", url = "${${env:}.url.account:}")
public interface AccountRemoteService {
	/**
	 * 查询余额账户
	 *
	 * dfs_518 <br/>
	 * 2017年6月13日 下午3:43:59 <br/>
	 * 
	 * @param userId
	 * @return
	 *
	 * 		ActServiceRlt<?>
	 */
	@RequestMapping(value = "/profitAccount/queryProfitAccount", method = RequestMethod.POST)
	ResultDto<QueryProfitAccountResp> queryProfitAccount(@RequestBody QueryProfitAccountReq queryProfitAccountReq);
}
