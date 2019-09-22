package com.prestamosprima.app.ws.service;

import java.math.BigDecimal;

import com.prestamosprima.app.ws.io.entity.PrimaryAccountEntity;
import com.prestamosprima.app.ws.io.entity.UserEntity;
import com.prestamosprima.app.ws.shared.dto.AccountDto;
import com.prestamosprima.app.ws.shared.dto.TransactionDto;
import com.prestamosprima.app.ws.shared.dto.UserDto;
import com.prestamosprima.app.ws.shared.exception.BusinessException;

public interface AccountService {
	AccountDto createAccount(UserDto userDto);
	AccountDto getAccount(Integer accountNumber);
	TransactionDto deposit(Integer accountNumber, BigDecimal amount, String description);
	TransactionDto withdraw(Integer accountNumber, BigDecimal amount, String description);

}
