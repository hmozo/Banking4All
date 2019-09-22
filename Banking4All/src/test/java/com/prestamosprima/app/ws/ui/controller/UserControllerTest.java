package com.prestamosprima.app.ws.ui.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.prestamosprima.app.ws.security.service.AuthorizationService;
import com.prestamosprima.app.ws.security.service.impl.AuthorizationServiceImpl;
import com.prestamosprima.app.ws.service.UserService;
import com.prestamosprima.app.ws.shared.dto.UserDto;
import com.prestamosprima.app.ws.shared.exception.BusinessException;
import com.prestamosprima.app.ws.ui.model.response.ResultRest;
import com.prestamosprima.app.ws.ui.model.response.UserRest;

class UserControllerTest {

	@InjectMocks
	UserController userController;
	
	@Mock
	AuthorizationServiceImpl authorizationService;
	
	@Mock
	UserService userService;
	
	@Mock
	HttpServletRequest request;
	
	@Mock
	HttpServletResponse response;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetUser() throws BusinessException {
		UserDto userDto= new UserDto();
		userDto.setId(1L);
		userDto.setUserId("HF45HFD");
		userDto.setFirstName("Sergey");
		userDto.setLastName("Prokov");
		userDto.setEmail("sergey@test.com");
		userDto.setPassword("456123");
		userDto.setEncryptedPassword("GSEASDG4");
		userDto.setAccountNumber(4561226);
		
		try {
			when(userService.getUser(Mockito.anyString()))
				.thenReturn(userDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		when(authorizationService.isUserAuthorized(request, response, ""))
			.thenReturn(Boolean.TRUE);
			
		ResultRest resultRest= new ResultRest();;
		try {
			resultRest = userController.getUser("", request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(resultRest.getData());
		assertEquals("sergey@test.com", ((UserRest)resultRest.getData()).getEmail());

		//assertEquals(4561226, ((UserRest)resultRest.getData()).getAccountNumber());
	}

}
