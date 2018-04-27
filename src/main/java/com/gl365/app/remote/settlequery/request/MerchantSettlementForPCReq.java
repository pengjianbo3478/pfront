package com.gl365.app.remote.settlequery.request;
import com.gl365.app.utils.GsonUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @author Haixiang.Zheng
 * @since 2018/2/6
 */
@ApiModel
public class MerchantSettlementForPCReq extends PageReq {
	/**
	 * 清算商户号
	 */
	@ApiModelProperty(value = "清算商户号", required = true)
	private String settleMerchantNo;
	// /**
	// * 开始日期
	// */
	// @ApiModelProperty(value = "开始日期", required = true)
	// private LocalDate fromDate;
	//
	// /**
	// * 结束日期
	// */
	// @ApiModelProperty(value = "结束日期", required = true)
	// private LocalDate toDate;

	public String getSettleMerchantNo() {
		return settleMerchantNo;
	}

	public void setSettleMerchantNo(String settleMerchantNo) {
		this.settleMerchantNo = settleMerchantNo;
	}

	@Override
	public String toString() {
		return GsonUtils.toJson(this);
	}
}
