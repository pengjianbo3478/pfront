package com.gl365.app.remote.settlequery.request;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
public class PageReq {
	@ApiModelProperty(value = "是否分页,0：查询所有数据 1：分页", example = "1")
	private String isPage = "1";
	@ApiModelProperty(value = "每页记录数,isPage=1时必填", example = "20")
	private int numPerPage;
	@ApiModelProperty(value = "页码,isPage=1时必填", example = "1")
	private int pageNum = 1;
	@ApiModelProperty(value = "每页记录数,isPage=1时必填", example = "20")
	private int pageSize;
	@ApiModelProperty(value = "开始日期:yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fromDate;
	@ApiModelProperty(value = "结束日期 :yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate toDate;

	public String getIsPage() {
		return isPage;
	}

	public void setIsPage(String isPage) {
		this.isPage = isPage;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.pageSize = numPerPage;
		this.numPerPage = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		this.numPerPage = pageSize;
	}
}
