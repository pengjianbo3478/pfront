package com.gl365.app.remote.merchant;
import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl365.app.dto.ResultDto;
import com.gl365.app.remote.member.request.UserForgotPwdDto;
import com.gl365.app.remote.member.request.UserLoginDto;
import com.gl365.app.remote.member.request.UserOperatorDto;
import com.gl365.app.remote.member.response.UserForgotPwdAdminListResp;
import com.gl365.app.remote.merchant.request.MerchantSaveOperatorReqDto;
import com.gl365.app.remote.merchant.request.MerchantUnBindOperatorReqDto;
import com.gl365.app.remote.merchant.request.MerchantUpdateOperatorReqDto;
import com.gl365.app.remote.merchant.request.UpdateMerchantInfoReqDto;
import com.gl365.app.remote.merchant.request.UploadPicDto;
import com.gl365.app.remote.merchant.response.MerchantBasicsInfoDto;
import com.gl365.app.remote.merchant.response.MerchantOperatorDto;
@FeignClient(name = "merchant", url = "${${env:}.url.merchant:}")
public interface MerchantRemoteService {
	// 获取商家详情
	@RequestMapping(value = "/merchant/info/detail", method = RequestMethod.GET)
	public Object getDetail(@RequestParam("merchantNo") String merchantNo);

	// 获取商家基本详情
	@RequestMapping(value = "/merchant/basics/detail", method = RequestMethod.GET)
	public ResultDto<MerchantBasicsInfoDto> getBasicsDetail(@RequestParam("merchantNo") String merchantNo);

	@RequestMapping(value = "/merchantAPI/user/login", method = RequestMethod.POST)
	public ResultDto<MerchantOperatorDto> login(@RequestBody UserLoginDto loginDto);

	@RequestMapping(value = "/merchantAPI/user/operator/updateById", method = RequestMethod.PUT)
	public ResultDto<Integer> updateById(UserOperatorDto userOperatorDto);

	@RequestMapping(value = "/merchantAPI/user/forgotPwdUnLock", method = RequestMethod.POST)
	public ResultDto<?> forgotPwdUnLock(@RequestParam("mobilePhone") String mobilePhone);

	// 管理员忘记密码
	@RequestMapping(value = "/merchantAPI/user/forgotPassword", method = RequestMethod.POST)
	public ResultDto<?> forgotPassword(@RequestBody UserForgotPwdDto userForgotPwdDto);

	@RequestMapping(value = "/merchantAPI/user/getAdminByMobile", method = RequestMethod.POST)
	public ResultDto<List<UserForgotPwdAdminListResp>> getAdminByMobile(@RequestParam("mobile") String mobile);
	
	// 明细
	@RequestMapping(value = "/merchant/operator/queryOperatorById", method = RequestMethod.GET)
	public ResultDto<MerchantOperatorDto> getOperatorById(@RequestParam("operatorId") String operatorId);

	// 未组装用户信息明细
	@RequestMapping(value = "/merchant/operator/queryOperatorByOperatorId", method = RequestMethod.GET)
	public ResultDto<MerchantOperatorDto> getOperatorByOperatorId(@RequestParam("operatorId") String operatorId);

	// 根据店长Id,获取店长直属员工列表
	@RequestMapping(value = "/merchant/operator/queryMerchantOpertatorByParentId", method = RequestMethod.GET)
	public ResultDto<List<MerchantOperatorDto>> getOpertatorListByParentId(@RequestParam("merchantNo") String merchantNo, @RequestParam("parentId") String parentId);

	// 根据员工名称获取员工userId列表
	@RequestMapping(value = "/merchant/operator/queryOperatorInfoByRealName", method = RequestMethod.GET)
	public List<String> queryOperatorInfoByRealName(@RequestParam("realName") String realName, @RequestParam("merchantNo") String merchantNo);
	
	@RequestMapping(value = "/merchant/manage/findMerchantInfoByNos", method = RequestMethod.POST)
	public ResultDto<List<MerchantBasicsInfoDto>> findMerchantInfoByNos(@RequestBody List<String> merchantNos);
	
	// 新增员工
	@RequestMapping(value = "/merchant/operator/detail", method = RequestMethod.POST)
	public ResultDto<MerchantOperatorDto> addStaff(@RequestBody MerchantSaveOperatorReqDto command);
	
	// 维护(更新员工)
	@RequestMapping(value = "/merchant/operator/detail", method = RequestMethod.PUT)
	public ResultDto<MerchantOperatorDto> updateOperator(@RequestBody MerchantUpdateOperatorReqDto command);
	
	// 解绑员工
	@RequestMapping(value = "/merchant/operator/unBind", method = RequestMethod.PUT)
	public ResultDto<MerchantOperatorDto> unBindOperator(@RequestBody MerchantUnBindOperatorReqDto command);
	
	//更新商户基本信息
	@RequestMapping(value = "/merchant/update", method = RequestMethod.PUT)
	public Object updateMerchantInfo(@RequestBody UpdateMerchantInfoReqDto command);
	
	//上传图片
	@RequestMapping(value = "/merchant/pic/upload", method = RequestMethod.PUT)
	public Object uploadPic(@RequestBody UploadPicDto uploadPicDto);
}
