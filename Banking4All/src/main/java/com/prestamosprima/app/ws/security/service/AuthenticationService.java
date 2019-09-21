package com.prestamosprima.app.ws.security.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prestamosprima.app.ws.ui.model.request.UserLoginRequestModel;

public interface AuthenticationService {
	Boolean isUserAuthenticated(UserLoginRequestModel userLogin,HttpServletResponse response);
}
