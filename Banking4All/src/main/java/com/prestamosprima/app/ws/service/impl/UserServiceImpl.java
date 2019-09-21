package com.prestamosprima.app.ws.service.impl;



import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prestamosprima.app.ws.io.entity.UserEntity;
import com.prestamosprima.app.ws.io.repositories.UserRepository;
import com.prestamosprima.app.ws.service.UserService;
import com.prestamosprima.app.ws.shared.Utils;
import com.prestamosprima.app.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired 
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
	public UserDto createUser(UserDto user) {
		
		//if user exists, throws exception
		if(userRepository.findByEmail(user.getEmail())!=null) throw new RuntimeException("Record already exists with email: " + user.getEmail());
		
		UserEntity userEntity= new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		//encrypting password
		try {
			userEntity.setEncryptedPassword(utils.encode(user.getPassword()));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		
		Integer length= 30;
		String publicUserId= utils.generateUserId(length);
		userEntity.setUserId(publicUserId);
		
		//saving User entity
		UserEntity storedUserDetails= userRepository.save(userEntity);
		UserDto returnValue= new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		
		return returnValue;
	}

	@Override
	public UserDto getUser(String userId) {
		UserDto userDto= new UserDto();
		UserEntity userEntity= userRepository.findByUserId(userId);
		
		if (userEntity==null) 
			throw new RuntimeException(userId);
		
		BeanUtils.copyProperties(userEntity, userDto);
		
		return userDto;
	}

}
