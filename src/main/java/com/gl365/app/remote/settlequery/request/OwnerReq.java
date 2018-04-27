package com.gl365.app.remote.settlequery.request;
import io.swagger.annotations.ApiModelProperty;
public class OwnerReq extends PageReq {
	@ApiModelProperty(value = "结算帐号对象类型", hidden = true)
	private String ownerType;
	@ApiModelProperty(value = "结算帐号ID", hidden = true)
	private String ownerId;

	public String getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
}
