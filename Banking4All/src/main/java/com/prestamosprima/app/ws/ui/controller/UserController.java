package com.prestamosprima.app.ws.ui.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prestamosprima.app.ws.security.service.AuthenticationService;
import com.prestamosprima.app.ws.security.service.AuthorizationService;
import com.prestamosprima.app.ws.service.UserService;
import com.prestamosprima.app.ws.shared.Utils;
import com.prestamosprima.app.ws.shared.dto.UserDto;
import com.prestamosprima.app.ws.ui.model.request.UserDetailsRequestModel;
import com.prestamosprima.app.ws.ui.model.request.UserLoginRequestModel;
import com.prestamosprima.app.ws.ui.model.response.ResultRest;
import com.prestamosprima.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("users")
public class UserController {

	private static Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@Autowired
	AuthenticationService authenticationService;

	@Autowired
	AuthorizationService authorizationService;

	// get User data
	@GetMapping(path = "/{userId}")
	public ResultRest getUser(@PathVariable String userId, HttpServletRequest request, HttpServletResponse response) {
		ResultRest resultRest= new ResultRest();
		UserRest userRest= new UserRest();
		log.debug("Getting User");
		//if valid token
		if(authorizationService.isUserAuthorized(request, response, userId)) {
			UserDto userDto= userService.getUser(userId);
			BeanUtils.copyProperties(userDto, userRest);
			resultRest.setData(userRest);
			resultRest.setStatus(Response.SC_OK);
			//if not valid token
		}else{
			resultRest.setStatus(Response.SC_FORBIDDEN);
			resultRest.setMessages("User not authorized");
		}
		return resultRest;
	}

	// create User
	@PostMapping
	public ResultRest createUser(@Validated() @RequestBody UserDetailsRequestModel userDetails) {
		ResultRest resultRest= new ResultRest();
		UserRest userRest = new UserRest();

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);

		log.debug("Creating User");
		UserDto createdUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createdUser, userRest);
		
		resultRest.setData(userRest);
		resultRest.setStatus(Response.SC_OK);
		return resultRest;
	}

	// User login
	@PostMapping("login")
	public ResultRest login(@Validated() @RequestBody UserLoginRequestModel userLogin, HttpServletResponse response) {
		ResultRest resultRest= new ResultRest();
		// authenticate User
		if (authenticationService.isUserAuthenticated(userLogin, response)){
			resultRest.setStatus(Response.SC_OK);
		}else {
			resultRest.setStatus(Response.SC_FORBIDDEN);
			resultRest.setMessages("User not authenticated");
		}
		return resultRest;
	}

}
