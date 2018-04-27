package com.gl365.app.remote.message;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gl365.app.dto.PageInfo;
import com.gl365.app.dto.ResultDto;
import com.gl365.app.remote.message.request.MsgReq;
@FeignClient(name = "message", url = "${${env:}.url.message:}")
public interface MessageRemoteService {
	/**
	 * 删除一条推送消息
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/message/deleteMsgById", method = RequestMethod.POST)
	public ResultDto<?> deleteMsgById(@RequestBody MsgReq req);

	/**
	 * 更新一条推送消息
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/message/updateMsgById", method = RequestMethod.POST)
	public ResultDto<?> updateMsgById(@RequestBody MsgReq req);

	/**
	 * 根据id,alias,appType,messageType,messageDel查询推送消息
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/message/getInfoByCondition", method = RequestMethod.POST)
	public ResultDto<PageInfo<MsgReq>> getInfoByCondition(@RequestBody MsgReq req);
}
