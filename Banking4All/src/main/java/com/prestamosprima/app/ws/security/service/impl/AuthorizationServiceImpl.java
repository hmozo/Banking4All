package com.prestamosprima.app.ws.security.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.springframework.stereotype.Service;

import com.prestamosprima.app.ws.security.SecurityConstants;
import com.prestamosprima.app.ws.security.service.AuthorizationService;
import com.prestamosprima.app.ws.shared.exception.BusinessException;

import io.jsonwebtoken.Jwts;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

	@Override
	public Boolean isUserAuthorized(HttpServletRequest request, HttpServletResponse response, String userId) {
		String token= request.getHeader(SecurityConstants.HEADER_STRING);
		
		///check the header
		if (token==null || !token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			//return Boolean.FALSE;
			throw new BusinessException(Response.SC_FORBIDDEN, "User not authenticated");
		}
		
		token= token.replace(SecurityConstants.TOKEN_PREFIX, "");
		String user= Jwts.parser()
			.setSigningKey(SecurityConstants.TOKEN_SECRET)
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
		
		Boolean isAuthorized= user.equals(userId);
		
		if(!isAuthorized) {
			throw new BusinessException(Response.SC_FORBIDDEN, "User not authenticated");
		}
		
		return isAuthorized;
	}

}
