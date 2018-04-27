package com.gl365.app.dto;
import java.util.List;
import io.swagger.annotations.ApiModelProperty;
/**
 * @author Chen, Zhuhui
 */
public class PageResult<T> {
	@ApiModelProperty(value = "当前页")
	private Integer pageNum;
	// 每页的数量
	@ApiModelProperty(value = "每页的数量")
	private Integer pageSize;
	// 当前页的数量
	@ApiModelProperty(value = "当前页的数量")
	private Integer size;
	// 由于startRow和endRow不常用，这里说个具体的用法
	// 可以在页面中"显示startRow到endRow 共size条数据"
	// 当前页面第一个元素在数据库中的行号
	@ApiModelProperty(value = "当前页面第一个元素在数据库中的行号")
	private Integer startRow;
	@ApiModelProperty(value = "当前页面最后一个元素在数据库中的行号")
	// 当前页面最后一个元素在数据库中的行号
	private Integer endRow;
	// 总记录数
	@ApiModelProperty(value = "总记录数")
	private Long total;
	// 总页数
	@ApiModelProperty(value = "总页数")
	private Integer pages;
	// 结果集
	@ApiModelProperty(value = "结果集")
	private List<T> list;
	// 前一页
	@ApiModelProperty(value = "前一页")
	private Integer prePage;
	// 下一页
	@ApiModelProperty(value = "下一页")
	private Integer nextPage;
	// 是否为第一页
	@ApiModelProperty(value = "是否为第一页")
	private Boolean isFirstPage = false;
	// 是否为最后一页
	@ApiModelProperty(value = "是否为最后一页")
	private Boolean isLastPage = false;
	// 是否有前一页
	@ApiModelProperty(value = "是否有前一页")
	private Boolean hasPreviousPage = false;
	// 是否有下一页
	@ApiModelProperty(value = "是否有下一页")
	private Boolean hasNextPage = false;
	// 导航页码数
	@ApiModelProperty(value = "导航页码数")
	private Integer navigatePages;
	// 所有导航页号
	@ApiModelProperty(value = "Integer")
	private Integer[] navigatepageNums;
	// 导航条上的第一页
	@ApiModelProperty(value = "导航条上的第一页")
	private Integer navigateFirstPage;
	// 导航条上的最后一页
	@ApiModelProperty(value = "导航条上的最后一页")
	private Integer navigateLastPage;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	public Integer getEndRow() {
		return endRow;
	}

	public void setEndRow(Integer endRow) {
		this.endRow = endRow;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Integer getPrePage() {
		return prePage;
	}

	public void setPrePage(Integer prePage) {
		this.prePage = prePage;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public Boolean getIsFirstPage() {
		return isFirstPage;
	}

	public void setIsFirstPage(Boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}

	public Boolean getIsLastPage() {
		return isLastPage;
	}

	public void setIsLastPage(Boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public Boolean getHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(Boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public Boolean getHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(Boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public Integer getNavigatePages() {
		return navigatePages;
	}

	public void setNavigatePages(Integer navigatePages) {
		this.navigatePages = navigatePages;
	}

	public Integer[] getNavigatepageNums() {
		return navigatepageNums;
	}

	public void setNavigatepageNums(Integer[] navigatepageNums) {
		this.navigatepageNums = navigatepageNums;
	}

	public Integer getNavigateFirstPage() {
		return navigateFirstPage;
	}

	public void setNavigateFirstPage(Integer navigateFirstPage) {
		this.navigateFirstPage = navigateFirstPage;
	}

	public Integer getNavigateLastPage() {
		return navigateLastPage;
	}

	public void setNavigateLastPage(Integer navigateLastPage) {
		this.navigateLastPage = navigateLastPage;
	}
}
