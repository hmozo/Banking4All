package com.prestamosprima.app.ws.service;

import com.prestamosprima.app.ws.shared.dto.UserDto;

public interface UserService {
	UserDto createUserAndAccount(UserDto user);
	UserDto getUser(String userId);
}
