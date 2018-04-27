package com.gl365.app.remote.payquery.request;
import io.swagger.annotations.ApiModelProperty;
public class QueryPagePayTotalByOperatorDetailReqDTO extends QueryPagePayTotalByOperatorReqDTO {
	@ApiModelProperty(value = "收款人 operatorType=1时，收款人ID")
	private String operator;
	
	@ApiModelProperty(value = "收款人类型 1.收银员 2.商家")
	private String operatorType;
	
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}
	
}
