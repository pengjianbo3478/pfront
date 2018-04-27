package com.gl365.app.remote.payquery.response;
import java.io.Serializable;
import org.apache.commons.lang.StringUtils;
import com.gl365.app.dto.PageResult;
import com.gl365.app.utils.GsonUtils;
import io.swagger.annotations.ApiModelProperty;
public class QueryPagePayTotalByOperatorDetailRespDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "交易返回码", hidden = true)
	private String resultCode;
	@ApiModelProperty(value = "交易返回描述", hidden = true)
	private String resultDesc;
	@ApiModelProperty(value = "明细列表数据")
	private PageResult<PayMain> resultData;
	@ApiModelProperty(value = "汇总数据")
	private QueryPagePayTotalByOperatorDetailTotalRespDTO resultDataTotal;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
		if (StringUtils.isNotBlank(resultCode) && resultCode.length() == 4) {
			this.resultCode = "00" + resultCode;
		}
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public PageResult<PayMain> getResultData() {
		return resultData;
	}

	public void setResultData(PageResult<PayMain> resultData) {
		this.resultData = resultData;
	}

	public QueryPagePayTotalByOperatorDetailTotalRespDTO getResultDataTotal() {
		return resultDataTotal;
	}

	public void setResultDataTotal(QueryPagePayTotalByOperatorDetailTotalRespDTO resultDataTotal) {
		this.resultDataTotal = resultDataTotal;
	}

	@Override
	public String toString() {
		return GsonUtils.toJson(this);
	}
}
