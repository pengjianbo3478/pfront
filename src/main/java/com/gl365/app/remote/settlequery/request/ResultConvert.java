package com.gl365.app.remote.settlequery.request;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.collections.CollectionUtils;
import com.gl365.app.dto.ResultDto;
import io.swagger.annotations.ApiModelProperty;
public class ResultConvert {
	@ApiModelProperty(value = "总记录数", hidden = true)
	private Integer totalNum;
	@ApiModelProperty(value = "数据集", required = false, example = "")
	private Object list;

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Object getList() {
		return list;
	}

	public void setList(Object list) {
		this.list = list;
	}

	public static Object resultConvert(ResultDto<?> rlt, Collection<?> dataSet) {
		ResultConvert data = new ResultConvert();
		data.setTotalNum(0);
		data.setList(new ArrayList<>());
		if (rlt != null && CollectionUtils.isNotEmpty(dataSet)) {
			data.setList(rlt.getData());
			data.setTotalNum(rlt.getTotalNum());
		}
		return ResultDto.result(rlt.getResult(), rlt.getDescription(), data);
	}
}
