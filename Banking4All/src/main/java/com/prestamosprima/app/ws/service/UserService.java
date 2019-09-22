package com.prestamosprima.app.ws.service;

import com.prestamosprima.app.ws.shared.dto.UserDto;
import com.prestamosprima.app.ws.shared.exception.BusinessException;

public interface UserService {
	UserDto createUserAndAccount(UserDto user);
	UserDto getUser(String userId);
}
