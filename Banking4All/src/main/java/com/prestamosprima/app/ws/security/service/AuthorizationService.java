package com.prestamosprima.app.ws.security.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthorizationService {
	Boolean isUserAuthorized(HttpServletRequest request, HttpServletResponse response, String userId);
}
