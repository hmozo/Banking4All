package com.prestamosprima.app.ws.ui.model.request;

import javax.validation.constraints.Email;

public class UserLoginRequestModel {
	@Email(message = "Email should be valid")
	private String email;
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
