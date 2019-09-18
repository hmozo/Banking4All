package com.prestamosprima.app.ws.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prestamosprima.app.ws.UserRepository;
import com.prestamosprima.app.ws.io.entity.UserEntity;
import com.prestamosprima.app.ws.service.UserService;
import com.prestamosprima.app.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired 
	UserRepository userRepository;
	
	//@Autowired
	//BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDto createUser(UserDto user) {
		
		//if user exists, throws exception
		if(userRepository.findByEmail(user.getEmail())!=null) throw new RuntimeException("Record already exists with email: " + user.getEmail());
		
		UserEntity userEntity= new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		userEntity.setEncryptedPassword(user.getPassword());
		//userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId("testUserId");
		
		//saving User entity
		UserEntity storedUserDetails= userRepository.save(userEntity);
		UserDto returnValue= new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		
		return user;
	}

}
