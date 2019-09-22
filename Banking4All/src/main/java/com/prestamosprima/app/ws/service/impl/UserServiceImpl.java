package com.prestamosprima.app.ws.service.impl;



import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prestamosprima.app.ws.io.entity.PrimaryAccountEntity;
import com.prestamosprima.app.ws.io.entity.UserEntity;
import com.prestamosprima.app.ws.io.repositories.AccountRepository;
import com.prestamosprima.app.ws.io.repositories.UserRepository;
import com.prestamosprima.app.ws.service.AccountService;
import com.prestamosprima.app.ws.service.UserService;
import com.prestamosprima.app.ws.shared.Utils;
import com.prestamosprima.app.ws.shared.dto.AccountDto;
import com.prestamosprima.app.ws.shared.dto.UserDto;
import com.prestamosprima.app.ws.ui.controller.UserController;

@Service
public class UserServiceImpl implements UserService {

	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired 
	UserRepository userRepository;
	
	@Autowired 
	AccountRepository accountRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	AccountService accountService;
	
	/**
	 * Cerate user (and Primary account)
	 */
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
	public UserDto createUserAndAccount(UserDto userDto) {
		log.debug("Creating User");
		UserEntity userEntityCreated= createUser(userDto);
		
		log.debug("Creating PrimaryAccount");
		PrimaryAccountEntity accountEntityCreated= createAccount(userDto);
		
		//set Account to the User
		userEntityCreated.setAccount(accountEntityCreated);
		
		UserDto returnUserDto= new UserDto();
		BeanUtils.copyProperties(userEntityCreated, returnUserDto);
		returnUserDto.setAccountNumber(userEntityCreated.getAccount().getAccountNumber());
		
		
		return returnUserDto;
	}

	

	private UserEntity createUser(UserDto userDto) {
		//if user exists, throws exception
		if(userRepository.findByEmail(userDto.getEmail())!=null) throw new RuntimeException("Record already exists with email: " + userDto.getEmail());
		
		UserEntity userEntity= new UserEntity();
		BeanUtils.copyProperties(userDto, userEntity);
		
		//encrypting password
		try {
			userEntity.setEncryptedPassword(utils.encode(userDto.getPassword()));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		
		Integer length= 30;
		String publicUserId= utils.generateUserId(length);
		userEntity.setUserId(publicUserId);
		
		//Creating User
		log.debug("Saving User");
		UserEntity userEntityStored= userRepository.save(userEntity);
		return userEntityStored;
		
	}
	
	private PrimaryAccountEntity createAccount(UserDto userDto) {
		//Creating Account
		log.debug("Creating Account");
		PrimaryAccountEntity accountEntity= accountService.createAccount(userDto);
		
		return accountEntity;
		
	}
	
	
	/**
	 * Get user
	 */
	@Override
	public UserDto getUser(String userId) {
		log.debug("Getting User");
		UserDto userDto= new UserDto();
		UserEntity userEntity= userRepository.findByUserId(userId);
		
		if (userEntity==null) 
			throw new RuntimeException(userId);
		
		BeanUtils.copyProperties(userEntity, userDto);
		
		return userDto;
	}

}
