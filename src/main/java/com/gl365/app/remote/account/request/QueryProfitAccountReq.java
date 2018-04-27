package com.gl365.app.remote.account.request;
public class QueryProfitAccountReq {
	private String accountId;// 账户ID String 32 否
	private String accountType;// 账户类型 6：员工,店长,会员

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
}
