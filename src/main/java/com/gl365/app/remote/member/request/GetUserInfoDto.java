package com.gl365.app.remote.member.request;

import com.gl365.app.remote.member.request.UserUpdateDto;

public class GetUserInfoDto extends UserUpdateDto {

	private static final long serialVersionUID = 4756821342088252212L;

	public GetUserInfoDto(String phone) {
		super();
		this.setMobilePhone(phone);
	}
}
