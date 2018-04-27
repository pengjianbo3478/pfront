package com.gl365.app.dto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import io.swagger.annotations.ApiModelProperty;
/**
 * < 页码DTO >
 * 
 * @author hui.li 2017年4月20日 - 下午7:36:25
 * @Since 1.0
 */
public class PageInfo<T> implements Serializable {
	private static final long serialVersionUID = -7498073051970322499L;

	public PageInfo() {
	}

	public PageInfo(Integer totalCount, Integer curPage, Integer pageSize, List<T> list, Map<String, Object> dataMap) {
		this.totalCount = totalCount; // 总条数
		this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1; // 总页数
		this.curPage = curPage; // 页码
		this.pageSize = pageSize; // 页数
		this.list = list;
		this.dataMap = dataMap;
	}

	public PageInfo(PageInfo<?> page, List<T> list) {
		this.totalCount = page.getTotalCount();
		this.totalPage = page.getTotalPage();
		this.curPage = page.getCurPage();
		this.pageSize = page.getPageSize();
		this.dataMap = page.getDataMap();
		this.list = list;
	}

	public PageInfo(PageInfo<?> page, List<T> list, Map<String, Object> dataMap) {
		this.totalCount = page.getTotalCount(); // 总条数
		this.totalPage = page.getTotalPage();
		this.curPage = page.getCurPage();
		this.pageSize = page.getPageSize();
		this.list = list;
		this.dataMap = dataMap;
	}
	@ApiModelProperty(value = "结果数据", required = true, example = "100")
	private Integer totalCount; // 总条数
	@ApiModelProperty(value = "结果数据", required = true, example = "10")
	private Integer totalPage; // 总页数
	@ApiModelProperty(value = "结果数据", required = true, example = "10")
	private Integer curPage; // 当前页
	@ApiModelProperty(value = "页Size", required = true, example = "10")
	private Integer pageSize; // 页Size
	private BigDecimal sumAmount;
	/**
	 * T : 数据集
	 */
	@ApiModelProperty(value = "数据集", required = false, example = "")
	private List<T> list;
	private Map<String, Object> dataMap;

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public BigDecimal getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}
}
