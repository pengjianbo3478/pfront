package com.gl365.app.web;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gl365.app.common.HttpParamConstant;
import com.gl365.app.common.Permis;
import com.gl365.app.common.PermisConstant;
import com.gl365.app.dto.ResultDto;
import com.gl365.app.enums.ResultCodeEnum;
import com.gl365.app.remote.merchant.MerchantRemoteService;
import com.gl365.app.remote.merchant.response.MerchantOperatorDto;
import com.gl365.app.service.RedisService;
/**
 * @author dfs_519 2017年5月6日下午12:08:55
 */
@Aspect
@Component
public class PermisController {
	private static final Logger logger = LoggerFactory.getLogger(PermisController.class);
	public static final int leave = 1;
	public static final Long LIVE_TIME = null;
	@Autowired
	private RedisService redisService;
	@Autowired
	private MerchantRemoteService merchantRemoteService;

	@Around("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(permis)")
	public Object requestPermis(ProceedingJoinPoint proceedingJoinPoint, Permis permis) throws Throwable {
		HttpServletRequest request = null;
		try {
			Object[] args = proceedingJoinPoint.getArgs();
			for (int i = 0; i < args.length; i++) {
				if (args[i] instanceof HttpServletRequest) {
					request = (HttpServletRequest) args[i];
					break;
				}
			}
			if (null == request) {
				logger.error("requestPermis handler error : target:[{}] no param : HttpServletRequest");
				ResultDto<String> data = new ResultDto<>();
				data.setResult(ResultCodeEnum.System.REQUEST_IS_NULL.getCode());
				data.setDescription(ResultCodeEnum.System.REQUEST_IS_NULL.getMsg());
				return data;
			}
			// 请求路径
			String path = request.getServletPath();
			// 这个userId就是operatorId，因为框架中的securityFilter里面定死了GL_APP_USER_ID无法改为operatorId
			String operatorId = (String) request.getSession().getAttribute(HttpParamConstant.Session.GL_APP_USER_ID);
			if (StringUtils.isEmpty(operatorId) || "null".equals(operatorId)) {
				logger.error("<====NEED_LOGIN====>requestPermis session's operatorId is null,must login first");
				return ResultDto.result(ResultCodeEnum.System.NEED_LOGIN);
			}
			// 从redis中获取operator，如果redis中没有，则从数据库取
			MerchantOperatorDto operator = (MerchantOperatorDto) redisService.get(operatorId);
			if (null == operator) {
				ResultDto<MerchantOperatorDto> rlt = merchantRemoteService.getOperatorById(operatorId);
				if (null != rlt && ResultCodeEnum.System.SUCCESS.getCode().equals(rlt.getResult())) {
					operator = rlt.getData();
					if (null == operator) {
						logger.error("requestPermis ===> operatorService.getOperatorById,operatorId={}, operator:{}", operatorId, operator);
						return ResultDto.result(ResultCodeEnum.System.NO_PERMISSION);
					}
					redisService.set(operatorId, operator, LIVE_TIME);
				}
				else {
					logger.error("requestPermis operatorId：{} is not permis to request path:{}", operatorId, path);
					return ResultDto.result(ResultCodeEnum.System.NO_PERMISSION);
				}
			}
			// 权限控制
			if (null != permis && StringUtils.isNotEmpty(permis.permission())) {
				if (leave == operator.getQuit()) {
					if (!permis.permission().contains(PermisConstant.Leave)) {
						logger.error("requestPermis operatorId：{},has leave:{}, not permis to request path:{}", operatorId, operator.getQuit(), path);
						return ResultDto.result(ResultCodeEnum.System.NO_PERMISSION);
					}
				}
				else {
					if (!permis.permission().contains(operator.getRoleId())) {
						logger.error("requestPermis operatorId：{},roleId：{} is not permis to request path:{}", operatorId, operator.getRoleId(), path);
						return ResultDto.result(ResultCodeEnum.System.NO_PERMISSION);
					}
				}
			}
			return proceedingJoinPoint.proceed();
		}
		catch (Exception e) {
			logger.error("requestPermis handler error,exception:{}", e);
			return ResultDto.errorResult();
		}
	}
}
