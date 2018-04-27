package com.gl365.app.remote.payquery.response;
import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.gl365.app.dto.PageResult;

import io.swagger.annotations.ApiModelProperty;
public class QueryPagePayMainRespDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "交易返回码", hidden = true)
	private String resultCode;
	@ApiModelProperty(value = "交易返回描述", hidden = true)
	private String resultDesc;
	@ApiModelProperty(value = "交易明细列表数据")
	private PageResult<PayMain> resultData;
	@ApiModelProperty(value = "交易明细汇总")
	private QueryPagePayMainTotalRespDTO resultDataTotal;

	/**
	 * @return the resultCode
	 */
	public String getResultCode() {
		return resultCode;
	}

	/**
	 * @param resultCode
	 *            the resultCode to set
	 */
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
		if (StringUtils.isNotBlank(resultCode) && resultCode.length() == 4) {
			this.resultCode = "00" + resultCode;
		}
	}

	/**
	 * @return the resultDesc
	 */
	public String getResultDesc() {
		return resultDesc;
	}

	/**
	 * @param resultDesc
	 *            the resultDesc to set
	 */
	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	/**
	 * @return the resultData
	 */
	public PageResult<PayMain> getResultData() {
		return resultData;
	}

	/**
	 * @param resultData
	 *            the resultData to set
	 */
	public void setResultData(PageResult<PayMain> resultData) {
		this.resultData = resultData;
	}

	public QueryPagePayMainTotalRespDTO getResultDataTotal() {
		return resultDataTotal;
	}

	public void setResultDataTotal(QueryPagePayMainTotalRespDTO resultDataTotal) {
		this.resultDataTotal = resultDataTotal;
	}
}
