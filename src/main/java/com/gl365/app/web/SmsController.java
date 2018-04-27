package com.gl365.app.web;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gl365.app.dto.ResultDto;
import com.gl365.app.enums.ResultCodeEnum;
import com.gl365.app.remote.member.MemberRemoteService;
import com.gl365.app.remote.member.request.SendSMSReq;
import com.gl365.app.remote.member.request.VerifySMSReq;
import io.swagger.annotations.Api;
@CrossOrigin(origins = "*", maxAge = 3600) // 跨域问题，与前端调试时打开
@Api(description = "短信相关接口")
@RestController
@RequestMapping("/businessAPI/sms")
public class SmsController {
	private static final Logger LOG = LoggerFactory.getLogger(SmsController.class);
	@Autowired
	private MemberRemoteService memberRemoteService;

	@PostMapping("/sendSms")
	public Object sendSms(@RequestBody SendSMSReq req) {
		LOG.info("sendSms begin,reqParam:{}", req.toString());
		ResultDto<?> resp = new ResultDto<>();
		if (StringUtils.isEmpty(req.getPhoneNo()) || req.getBusinessType() == null) {
			resp.setResult(ResultCodeEnum.System.PARAM_NULL.getCode());
			resp.setDescription(ResultCodeEnum.System.PARAM_NULL.getMsg());
			return resp;
		}
		try {
			resp = memberRemoteService.sendSms(req);
		}
		catch (Exception e) {
			LOG.error("sendSms ===> smsService.sendSms exception,e：" + e);
			resp.setResult(ResultCodeEnum.System.SYSTEM_ERROR.getCode());
			resp.setDescription(ResultCodeEnum.System.SYSTEM_ERROR.getMsg());
		}
		return resp;
	}

	@PostMapping("/verifySms")
	public Object verifySmsCode(@RequestBody VerifySMSReq req) {
		LOG.info("SmsCode verify,reqParam:{}", req.toString());
		try {
			if (StringUtils.isEmpty(req.getPhoneNo()) || req.getBusinessType() == null) return ResultDto.result(ResultCodeEnum.System.PARAM_NULL);
			ResultDto<?> resp = memberRemoteService.verifySmsCode(req);
			Map<String, Object> result = new HashMap<>();
			if (ResultCodeEnum.System.SUCCESS.getCode().equals(resp.getResult())) {
				result.put("valid", true);
			}
			else {
				result.put("valid", false);
			}
			return new ResultDto<>(resp.getResult(), resp.getDescription(), result);
		}
		catch (Exception e) {
			LOG.error("SmsCode verify ===> smsService.verify exception,e：" + e);
			return ResultDto.errorResult();
		}
	}
}
