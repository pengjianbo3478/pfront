package com.gl365.app.remote.settlequery.request;
import io.swagger.annotations.ApiModelProperty;
public class ProfitDetailedReq extends OwnerReq {
	@ApiModelProperty(value = "提成大类 1：营销费提成,3：首笔交易奖励,5：打赏奖励,6：收银奖励,如为空，则查询以上4种")
	private String parentProfitType;

	public String getParentProfitType() {
		return parentProfitType;
	}

	public void setParentProfitType(String parentProfitType) {
		this.parentProfitType = parentProfitType;
	}
}
