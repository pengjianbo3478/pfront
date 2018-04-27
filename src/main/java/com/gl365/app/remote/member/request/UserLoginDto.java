package com.gl365.app.remote.member.request;

import com.gl365.app.dto.LoginCommand;

public class UserLoginDto {
	private LoginCommand command;
	private UserUpdateDto merchantUser;

	public LoginCommand getCommand() {
		return command;
	}

	public void setCommand(LoginCommand command) {
		this.command = command;
	}

	public UserUpdateDto getMerchantUser() {
		return merchantUser;
	}

	public void setMerchantUser(UserUpdateDto merchantUser) {
		this.merchantUser = merchantUser;
	}
}
