package com.gl365.app.remote.validator;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.gl365.app.dto.ResultDto;
@FeignClient(name = "validator", url = "${${env:}.url.validator:}")
public interface ValidatorRemoteService {
	/**
	 * 实名认证
	 * @param cardId 身份证
	 * @param name 名字
	 * @return ResultDto<Boolean>
	 */
	@RequestMapping(value = "/validIdCard", method = RequestMethod.POST)
	public ResultDto<Boolean> certification(@RequestParam("cardId") String cardId, @RequestParam("name") String name);

	/**
	* 验证银行卡
	*
	* dfs_518 <br/>
	* 2017年5月19日 下午4:29:13 <br/>
	* @param bankId
	* @param cardId
	* @param name
	* @return ResultDto<Boolean>
	*/
	@RequestMapping(value = "/validBankCard", method = RequestMethod.POST)
	public ResultDto<Boolean> validBankCard(@RequestParam("bankId") String bankId, @RequestParam("cardId") String cardId, @RequestParam("name") String name);
}
