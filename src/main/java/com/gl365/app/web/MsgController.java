package com.gl365.app.web;
import java.util.ArrayList;
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
import com.gl365.app.dto.PageInfo;
import com.gl365.app.dto.ResultDto;
import com.gl365.app.enums.ResultCodeEnum;
import com.gl365.app.remote.merchant.response.MerchantOperatorDto;
import com.gl365.app.remote.message.MessageRemoteService;
import com.gl365.app.remote.message.request.MsgReq;
import com.gl365.app.service.RedisService;
import com.gl365.app.utils.JsonUtils;
import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins = "*", maxAge = 3600) // 跨域问题，与前端调试时打开
@RestController
@RequestMapping("/businessAPI/msg")
public class MsgController {
	private static final Logger LOG = LoggerFactory.getLogger(MsgController.class);
	@Autowired
	private MessageRemoteService messageRemoteService;
	@Autowired
	private RedisService redisService;

	@ApiOperation("批量删除消息")
	@PostMapping("/delete")
	@Log
	public Object delete(@RequestBody MsgReq msgReq) {
		LOG.info("msg delete begin param={}", JsonUtils.toJsonString(msgReq));
		Long beginTime = System.currentTimeMillis();
		ResultDto<?> rlt = ResultDto.result(ResultCodeEnum.System.SUCCESS);
		try {
			List<Long> req = msgReq.getIdList();
			if (req != null && (!req.isEmpty())) {
				Integer count = 20;
				if (req.size() > count) { return new ResultDto<>(ResultCodeEnum.System.SYSTEM_ERROR.getCode(), "删除条数不可超过" + count + "条", null); }
				for (Long recordId : req) {
					MsgReq target = new MsgReq();
					target.setId(recordId);
					ResultDto<?> result = messageRemoteService.deleteMsgById(target);
					LOG.info("msg [id={}] result={}", recordId, JsonUtils.toJsonString(result));
				}
			}
		}
		catch (Exception e) {
			LOG.error("msg delete exception e:{}", e);
			rlt = ResultDto.result(ResultCodeEnum.System.SYSTEM_ERROR);
		}
		Long endTime = System.currentTimeMillis();
		LOG.info("msg delete end rlt={},time={}ms", JsonUtils.toJsonString(rlt), (endTime - beginTime));
		return rlt;
	}

	@ApiOperation("设置此条消息已读")
	@PostMapping("/readMsg")
	@Log
	public Object read(@RequestBody MsgReq req) {
		LOG.info("msg read begin param={}", JsonUtils.toJsonString(req));
		Long beginTime = System.currentTimeMillis();
		ResultDto<?> rlt = null;
		try {
			if (req.getId() == null) { return ResultDto.result(ResultCodeEnum.System.PARAM_NULL); }
			MsgReq target = new MsgReq();
			target.setId(req.getId());
			target.setMessageRead("01");
			if ("01".equals(req.getMessageRead())) {
				rlt = new ResultDto<>(ResultCodeEnum.System.SUCCESS);
			}
			else {
				rlt = messageRemoteService.updateMsgById(target);
			}
		}
		catch (Exception e) {
			LOG.error("msg read exception e:{}", e);
			rlt = ResultDto.result(ResultCodeEnum.System.SYSTEM_ERROR);
		}
		Long endTime = System.currentTimeMillis();
		LOG.info("msg read end rlt={},time={}ms", JsonUtils.toJsonString(rlt), (endTime - beginTime));
		return rlt;
	}

	@ApiOperation("查询消息列表")
	@PostMapping("/getMsg")
	@Permis
	@Log
	public ResultDto<PageInfo<MsgReq>> getInfoByCondition(HttpServletRequest request, @RequestBody MsgReq req) {
		LOG.info("msg getInfoByCondition begin param={}", JsonUtils.toJsonString(req));
		Long beginTime = System.currentTimeMillis();
		ResultDto<PageInfo<MsgReq>> rlt = null;
		try {
			MerchantOperatorDto merchantOperatorDto = getOperator(request);
			if (merchantOperatorDto == null) {
				PageInfo<MsgReq> data = new PageInfo<>(0, req.getCurPage(), req.getPageSize(), new ArrayList<>(), null);
				LOG.info("查询消息列表获取缓存数据为空");
				return ResultDto.result(ResultCodeEnum.System.SUCCESS, data);
			}
			String alias = merchantOperatorDto.getUserId();
			if (PermisConstant.ADMIN.equals(merchantOperatorDto.getRoleId())) {
				alias = merchantOperatorDto.getOperatorId();
			}
			req.setAlias(alias);
			req.setMessageDel("00");
			req.setAppType("bfront");
			rlt = messageRemoteService.getInfoByCondition(req);
		}
		catch (Exception e) {
			LOG.error("msg getInfoByCondition exception e:{}", e);
			rlt = ResultDto.result(ResultCodeEnum.System.SYSTEM_ERROR, null);
		}
		Long endTime = System.currentTimeMillis();
		LOG.info("msg getInfoByCondition end rlt={},time={}ms", JsonUtils.toJsonString(rlt), (endTime - beginTime));
		return rlt;
	}

	private MerchantOperatorDto getOperator(HttpServletRequest request) {
		// 没有直接返回Null 调用处抛系统异常,因为不存在这种情况
		Object userId = request.getSession().getAttribute(HttpParamConstant.Session.GL_APP_USER_ID);
		if (null == userId) return null;
		Object redis = redisService.get(userId.toString());
		if (null == redis || !(redis instanceof MerchantOperatorDto)) return null;
		MerchantOperatorDto opr = (MerchantOperatorDto) redis;
		return opr;
	}
}
