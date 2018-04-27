package com.gl365.app.remote.order;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gl365.app.dto.ResultDto;
import com.gl365.app.remote.order.request.OrderRefundReq;
/**
 * 订单系统服务
 * @author dfs_519
 *2017年6月17日下午3:18:45
 */
@FeignClient(name = "order", url = "${${env:}.url.order:}")
public interface OrderRemoteService {
	/**
	 * 退款接口
	 */
	@RequestMapping(value = "/order/rm/refund", method = RequestMethod.POST)
	public ResultDto<?> refund(@RequestBody OrderRefundReq req);
}