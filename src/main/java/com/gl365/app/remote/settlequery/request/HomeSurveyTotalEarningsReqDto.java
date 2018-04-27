package com.gl365.app.remote.settlequery.request;

import java.util.List;
import io.swagger.annotations.ApiModelProperty;

public class HomeSurveyTotalEarningsReqDto {

	@ApiModelProperty(value = "提成对象类型")
	private String ownerType;
	@ApiModelProperty(value = "查询列表")
	private List<String> queryData;
	
	public String getOwnerType() {
		return ownerType;
	}
	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}
	public List<String> getQueryData() {
		return queryData;
	}
	public void setQueryData(List<String> queryData) {
		this.queryData = queryData;
	}
}
