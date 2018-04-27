package com.gl365.app.remote.member;
import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gl365.app.dto.ResultDto;
import com.gl365.app.remote.member.request.SendSMSReq;
import com.gl365.app.remote.member.request.UserDto;
import com.gl365.app.remote.member.request.UserForgotPwdDto;
import com.gl365.app.remote.member.request.UserUpdateDto;
import com.gl365.app.remote.member.request.ValidatePwdReq;
import com.gl365.app.remote.member.request.VerifySMSReq;
/**
 * < 短信验证接口消费 >
 * 
 * @author hui.li 2017年4月21日 - 上午11:19:59
 * @Since 1.0
 */
@FeignClient(name = "member", url = "${${env:}.url.member:}")
public interface MemberRemoteService {
	/**
	 * 发送短信验证码接口
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/member/sms/sendSms")
	public ResultDto<String> sendSms(@RequestBody SendSMSReq req);

	/**
	 * 验证短信验证码
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/member/sms/verifySmsCode")
	public ResultDto<?> verifySmsCode(@RequestBody VerifySMSReq req);
	
	
	@RequestMapping(value = "/member/user/info/mobilePhone", method = RequestMethod.POST)
	public UserUpdateDto getUserInfoByMobilePhone(@RequestBody UserUpdateDto userDto);

	/**
	 * 根据userid用户信息
	 * 
	 * @param userDto
	 * @return
	 */
	@RequestMapping(value = "/member/user/info/userId", method = RequestMethod.POST)
	public UserUpdateDto getUserInfoByUserId(@RequestBody UserDto userDto);

	/**
	 * 修改用户
	 * @param UserDto
	 * @return
	 */
	@RequestMapping(value = "/member/user/updateUserByUserId", method = RequestMethod.PUT)
	public ResultDto<Integer> updateUserByUserId(@RequestBody UserUpdateDto userUpdateDto);

	/**
	 * 忘记密码
	 * @param userForgotPwdDto
	 * @return
	 */
	@RequestMapping(value = "/member/user/forgotPassword", method = RequestMethod.POST)
	public ResultDto<?> forgotPassword(@RequestBody UserForgotPwdDto userForgotPwdDto);

	/**
	 * 批量查询用户信息
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/member/user/getUserByuserIdList", method = RequestMethod.POST)
	public List<UserUpdateDto> getUserByuserIdList(@RequestBody List<String> userId);

	@RequestMapping(value = "/member/user/validatePassword", method = RequestMethod.POST)
	public Boolean validatePassword(@RequestBody ValidatePwdReq req);
}
