package com.prestamosprima.app.ws.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prestamosprima.app.ws.service.UserService;
import com.prestamosprima.app.ws.shared.dto.UserDto;
import com.prestamosprima.app.ws.ui.model.request.UserDetailsRequestModel;
import com.prestamosprima.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping
	public String getUser() {
		return "get user was called";
	}
	
	//create user
	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
		UserRest returnValue= new UserRest();
		
		UserDto userDto= new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto createdUser= userService.createUser(userDto);
		BeanUtils.copyProperties(createdUser, returnValue);
		
		
		return returnValue;
	}
	
	
}
