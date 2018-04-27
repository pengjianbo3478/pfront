package com.gl365.app.remote.account.response;
import java.math.BigDecimal;
import com.gl365.app.utils.BigDecimaluitl;
import io.swagger.annotations.ApiModelProperty;
public class QueryProfitAccountResp {
	@ApiModelProperty(value = "账户余额", required = true, example = "1000")
	private BigDecimal balance;

	public String getBalance() {
		return BigDecimaluitl.setScaleStr(balance);
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
