package com.gl365.app.web;
import java.util.Locale;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.gl365.app.dto.ResultDto;
import com.gl365.app.enums.ResultCodeEnum;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.netflix.hystrix.exception.HystrixTimeoutException;
/**
 * 
 * @author dfs_518
 *
 *         2017年9月19日下午2:43:26
 */
@ControllerAdvice
public class FacadeControllerAdvice extends ResponseEntityExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(FacadeControllerAdvice.class);
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(value = { HystrixBadRequestException.class, HystrixRuntimeException.class, HystrixTimeoutException.class, Exception.class })
	public ResponseEntity<ResultDto<Void>> handleGeiLeManagementSystemException(Exception ex) throws Throwable {
		logger.error("===HystrixRuntimeException==>" + ex.getMessage() + ":" + ex.getLocalizedMessage(), ex);
		ResultDto<Void> response = new ResultDto<>();
		response.setResult(ResultCodeEnum.System.SYSTEM_ERROR.getCode());
		response.setResultDesc(ResultCodeEnum.System.SYSTEM_ERROR.getMsg());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult bindingResult = ex.getBindingResult();
		return ResponseEntity.badRequest().body(buildApiResponseForBindingErrors(ex, bindingResult));
	}

	private ResultDto<Void> buildApiResponseForBindingErrors(MethodArgumentNotValidException ex, BindingResult bindingResult) {
		ResultDto<Void> response = new ResultDto<>();
		response.setResult(ResultCodeEnum.System.PARAM_ERROR.getCode());
		String message = bindingResult.getAllErrors().stream().map(oe -> messageSource.getMessage(oe, Locale.getDefault())).collect(Collectors.joining(", "));
		response.setResultDesc(message);
		logger.error(String.format("===MethodArgumentNotValidException==>[%s]", message), ex);
		return response;
	}
}
