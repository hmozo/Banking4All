package com.prestamosprima.app.ws.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.prestamosprima.app.ws.io.entity.PrimaryAccountEntity;
import com.prestamosprima.app.ws.io.entity.UserEntity;
import com.prestamosprima.app.ws.io.repositories.AccountRepository;
import com.prestamosprima.app.ws.io.repositories.UserRepository;
import com.prestamosprima.app.ws.service.AccountService;
import com.prestamosprima.app.ws.shared.dto.AccountDto;

class AccountServiceImplTest {

	@InjectMocks
	AccountServiceImpl accountService;
	
	@Mock
	AccountRepository accountRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetAccount() {
		
		PrimaryAccountEntity primaryAccountEntity= new PrimaryAccountEntity();
		primaryAccountEntity.setId(1L);
		primaryAccountEntity.setAccountNumber(11223146);
		primaryAccountEntity.setAccountBalance(new BigDecimal(25.3));
		primaryAccountEntity.setAccountStatement(new BigDecimal(12.45));
		primaryAccountEntity.setOverdraft(new BigDecimal(400));
		UserEntity userEntity= new UserEntity();
		userEntity.setId(1L);
		userEntity.setUserId("GSAD45DG");
		userEntity.setFirstName("Sergey");
		userEntity.setLastName("Prokov");
		userEntity.setEmail("sergey@test.com");
		userEntity.setEncryptedPassword("HGFSADH456CGD5");
		primaryAccountEntity.setUser(userEntity);
		
		when(accountRepository.findByAccountNumber(Mockito.anyInt()))
			.thenReturn(primaryAccountEntity);
		
		AccountDto accountDto= accountService.getAccount(25);
		
		assertEquals(11223146, accountDto.getAccountNumber());
		assertEquals(new BigDecimal(25.3), accountDto.getAccountBalance());
		
	}

}
