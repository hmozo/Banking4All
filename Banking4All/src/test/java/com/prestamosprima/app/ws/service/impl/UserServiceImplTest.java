package com.prestamosprima.app.ws.service.impl;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.prestamosprima.app.ws.io.entity.UserEntity;
import com.prestamosprima.app.ws.io.repositories.UserRepository;
import com.prestamosprima.app.ws.shared.dto.UserDto;

class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userService;
	
	@Mock
	UserRepository userRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	final void testGetUser() {
		UserEntity userEntity= new UserEntity();
		userEntity.setId(1L);
		userEntity.setUserId("GSAD45DG");
		userEntity.setFirstName("Sergey");
		userEntity.setLastName("Prokov");
		userEntity.setEmail("sergey@test.com");
		userEntity.setEncryptedPassword("HGFSADH456CGD5");
		
		when(userRepository.findByUserId(Mockito.anyString()))
			.thenReturn(userEntity);
		
		UserDto userDto= userService.getUser("testId");
		
		assertNotNull(userDto);
		assertEquals("Sergey", userDto.getFirstName());
		
	}
	
	@Test
	final void testGetUser_RunTimeException() {
		when(userRepository.findByUserId(Mockito.anyString()))
		.thenReturn(null);
		

		assertThrows(RuntimeException.class, ()->{
			userService.getUser("testId");
		});
		
		
	}

}
