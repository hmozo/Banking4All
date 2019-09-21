package com.prestamosprima.app.ws.security.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.prestamosprima.app.ws.security.SecurityConstants;
import com.prestamosprima.app.ws.security.service.AuthorizationService;

import io.jsonwebtoken.Jwts;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

	@Override
	public Boolean isUserAuthorized(HttpServletRequest request, HttpServletResponse response, String userId) {
		String token= request.getHeader(SecurityConstants.HEADER_STRING);
		
		///check the header
		if (token==null || !token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			return Boolean.FALSE;
		}
		
		token= token.replace(SecurityConstants.TOKEN_PREFIX, "");
		String user= Jwts.parser()
			.setSigningKey(SecurityConstants.TOKEN_SECRET)
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
		
		
		
		return user.equals(userId);
	}

}
