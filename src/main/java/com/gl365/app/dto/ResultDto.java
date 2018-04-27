package com.gl365.app.dto;
import java.io.Serializable;
import org.apache.commons.lang.StringUtils;
import com.gl365.app.enums.ResultCodeEnum;
import com.gl365.app.utils.JsonUtils;
import io.swagger.annotations.ApiModelProperty;
/**
 * < 基础响应 >
 * 
 * 包含常规请求响应的参数
 * 
 * @author hui.li 2017年4月12日 - 下午1:22:21
 * @Since 1.0
 */
public class ResultDto<T> implements Serializable {
	private static final long serialVersionUID = 5598379493227689413L;
	/**
	 * result : 响应码
	 */
	@ApiModelProperty(value = "响应码", required = true, example = "000000")
	private String result;
	/**
	 * description ： 响应描述
	 */
	@ApiModelProperty(value = "响应描述", required = false, example = "成功")
	private String description;
	/**
	 * data : 结果数据
	 */
	@ApiModelProperty(value = "结果数据", required = false, example = "")
	private T data;
	@ApiModelProperty(value = "响应码", hidden = true)
	private String resultCode;
	@ApiModelProperty(value = "响应描述", hidden = true)
	private String resultDesc;
	@ApiModelProperty(value = "结果数据", hidden = true)
	private T resultData;
	@ApiModelProperty(value = "总记录数", hidden = true)
	private Integer totalNum;

	public ResultDto() {
	}

	public ResultDto(T data) {
		this.data = data;
	}

	public ResultDto(String result, String description, T data) {
		this.result = result;
		this.description = description;
		this.data = data;
	}

	public ResultDto(ResultCodeEnum.System source) {
		this.result = source.getCode();
		this.description = source.getMsg();
	}

	public ResultDto(ResultCodeEnum.System source, T data) {
		this.result = source.getCode();
		this.description = source.getMsg();
		this.data = data;
	}

	public ResultDto(ResultCodeEnum.User source) {
		this.result = source.getCode();
		this.description = source.getMsg();
	}

	public static ResultDto<?> result(ResultDto<?> source, Object data) {
		ResultDto<?> target = new ResultDto<>(data);
		target.setResult(source.getResult());
		target.setDescription(source.getDescription());
		return target;
	}

	/***
	 * 
	 * System
	 */
	public static ResultDto<?> SUCCESS() {
		ResultDto<?> result = new ResultDto<>();
		result.setResult(ResultCodeEnum.System.SUCCESS.getCode());
		result.setDescription(ResultCodeEnum.System.SUCCESS.getMsg());
		result.setData(null);
		return result;
	}

	public static ResultDto<?> getErrInfo() {
		ResultDto<?> result = new ResultDto<>();
		result.setResult(ResultCodeEnum.System.SYSTEM_ERROR.getCode());
		result.setDescription(ResultCodeEnum.System.SYSTEM_ERROR.getMsg());
		result.setData(null);
		return result;
	}

	public static ResultDto<?> errorResult() {
		ResultDto<?> result = new ResultDto<>();
		result.setResult(ResultCodeEnum.System.SYSTEM_ERROR.getCode());
		result.setDescription(ResultCodeEnum.System.SYSTEM_ERROR.getMsg());
		return result;
	}

	public static ResultDto<?> result(ResultCodeEnum.System source) {
		ResultDto<?> result = new ResultDto<>();
		result.setResult(source.getCode());
		result.setDescription(source.getMsg());
		return result;
	}

	public static ResultDto<?> result(ResultCodeEnum.Payment source) {
		ResultDto<?> result = new ResultDto<>();
		result.setResult(source.getCode());
		result.setDescription(source.getMsg());
		return result;
	}

	public static <T> ResultDto<T> result(ResultCodeEnum.System source, T data) {
		ResultDto<T> result = new ResultDto<T>(data);
		result.setResult(source.getCode());
		result.setDescription(source.getMsg());
		return result;
	}

	public static <T> ResultDto<T> result(ResultCodeEnum.System source, String message, T data) {
		ResultDto<T> result = new ResultDto<T>(data);
		result.setResult(source.getCode());
		result.setDescription(message);
		return result;
	}

	/***
	 * Model
	 */
	public static ResultDto<?> result(ResultCodeEnum.User source) {
		ResultDto<?> result = new ResultDto<>();
		result.setResult(source.getCode());
		result.setDescription(source.getMsg());
		return result;
	}

	public static ResultDto<?> result(ResultCodeEnum.User source, Object data) {
		ResultDto<?> result = new ResultDto<>(data);
		result.setResult(source.getCode());
		result.setDescription(source.getMsg());
		return result;
	}

	public static ResultDto<?> result(ResultCodeEnum.User source, String message, Object data) {
		ResultDto<?> result = new ResultDto<>(data);
		result.setResult(source.getCode());
		result.setDescription(message);
		return result;
	}

	public static ResultDto<?> result(ResultCodeEnum.Merchant source, Object data) {
		ResultDto<?> result = new ResultDto<>(data);
		result.setResult(source.getCode());
		result.setDescription(source.getMsg());
		return result;
	}

	public static ResultDto<?> result(ResultCodeEnum.Merchant source, String message, Object data) {
		ResultDto<?> result = new ResultDto<>(data);
		result.setResult(source.getCode());
		result.setDescription(message);
		return result;
	}

	public static ResultDto<?> result(ResultCodeEnum.Customize source, Object data) {
		ResultDto<?> result = new ResultDto<>(data);
		result.setResult(source.getCode());
		result.setDescription(source.getMsg());
		return result;
	}

	public static ResultDto<?> result(ResultCodeEnum.Customize source, String message, Object data) {
		ResultDto<?> result = new ResultDto<>(data);
		result.setResult(source.getCode());
		result.setDescription(message);
		return result;
	}

	public static ResultDto<?> result(ResultCodeEnum.Payment source, Object data) {
		ResultDto<?> result = new ResultDto<>(data);
		result.setResult(source.getCode());
		result.setDescription(source.getMsg());
		return result;
	}

	public static ResultDto<?> result(ResultCodeEnum.Payment source, String message, Object data) {
		ResultDto<?> result = new ResultDto<>(data);
		result.setResult(source.getCode());
		result.setDescription(message);
		return result;
	}

	public static ResultDto<?> result(ResultCodeEnum.Sms source, Object data) {
		ResultDto<?> result = new ResultDto<>(data);
		result.setResult(source.getCode());
		result.setDescription(source.getMsg());
		return result;
	}

	public static ResultDto<?> result(ResultCodeEnum.Sms source, String message, Object data) {
		ResultDto<?> result = new ResultDto<>(data);
		result.setResult(source.getCode());
		result.setDescription(message);
		return result;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
		if (StringUtils.isNotBlank(resultCode)) {
			if (resultCode.length() == 4) {
				this.result = "00" + resultCode;
			}
			else {
				this.result = resultCode;
			}
		}
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
		if (StringUtils.isNotBlank(resultDesc)) this.description = resultDesc;
	}

	public void setResultData(T resultData) {
		this.resultData = resultData;
		if (resultData != null) this.data = resultData;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	@Override
	public String toString() {
		return JsonUtils.toJsonString(this);
	}

	public static ResultDto<?> settlementRltConvert(String settlementRltCode, ResultCodeEnum.Settlement source) {
		ResultDto<?> resp = new ResultDto<>();
		if (StringUtils.isEmpty(settlementRltCode)) {
			resp.setResult(ResultCodeEnum.System.SYSTEM_ERROR.getCode());
			resp.setDescription(ResultCodeEnum.System.SYSTEM_ERROR.getMsg());
		}
		else if ("000001".equals(settlementRltCode) || "999999".equals(settlementRltCode)) {
			resp.setResult(source.getCode());
			resp.setDescription(source.getMsg());
		}
		return resp;
	}

	public static ResultDto<?> result(ResultCodeEnum.Settlement source) {
		ResultDto<?> result = new ResultDto<>();
		result.setResult(source.getCode());
		result.setDescription(source.getMsg());
		return result;
	}

	public static ResultDto<?> result(String code, String msg) {
		ResultDto<?> result = new ResultDto<>();
		result.setResult(code);
		result.setDescription(msg);
		return result;
	}

	public static ResultDto<?> result(String code, String msg, Object data) {
		ResultDto<?> result = new ResultDto<>(data);
		result.setResult(code);
		result.setDescription(msg);
		return result;
	}
}
