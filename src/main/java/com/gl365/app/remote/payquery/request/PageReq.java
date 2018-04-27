package com.gl365.app.remote.payquery.request;
import io.swagger.annotations.ApiModelProperty;
public class PageReq {
	@ApiModelProperty(value = "分页第几页")
	private int pageNum = 1;
	@ApiModelProperty(value = "每页数量")
	private int pageSize = 10;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
