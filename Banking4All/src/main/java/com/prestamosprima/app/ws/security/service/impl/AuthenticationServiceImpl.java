package com.prestamosprima.app.ws.security.service.impl;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prestamosprima.app.ws.io.entity.UserEntity;
import com.prestamosprima.app.ws.io.repositories.UserRepository;
import com.prestamosprima.app.ws.security.SecurityConstants;
import com.prestamosprima.app.ws.security.service.AuthenticationService;
import com.prestamosprima.app.ws.shared.Utils;
import com.prestamosprima.app.ws.ui.model.request.UserLoginRequestModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

	@Autowired
	Utils utils;
	
	@Autowired
	UserRepository userRepository;
	

	/**
	 * Authenticate User
	 */
	@Override
	public Boolean isUserAuthenticated(UserLoginRequestModel userLogin, HttpServletResponse response) {
		Boolean isAuthenticated = this.authenticate(userLogin.getEmail(), userLogin.getPassword());
		// If user is correctly authenticated, token is sent within the header
		if (isAuthenticated) {
			this.successfulAuthentication(userLogin.getEmail(), response);
		} 
		return isAuthenticated;
	}
	
	
	private Boolean authenticate(String email, String password) {
		UserEntity userEntity= userRepository.findByEmail(email);
		Boolean isAuthenticated= Boolean.FALSE;
		//if User exists, check password matches
		if(userEntity!=null) {
			isAuthenticated= utils.checkEncode(password, userEntity.getEncryptedPassword());
		}else {
			throw new RuntimeException("User doesn't exist");
		}
		return isAuthenticated;
	}

	
	private void successfulAuthentication(String email, HttpServletResponse response) {
		UserEntity userEntity= userRepository.findByEmail(email);
		String token= Jwts.builder()
			.setSubject(userEntity.getUserId())
			.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
			.signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET)
			.compact();
		
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		response.addHeader("userId", userEntity.getUserId());
	}



	
	

}
