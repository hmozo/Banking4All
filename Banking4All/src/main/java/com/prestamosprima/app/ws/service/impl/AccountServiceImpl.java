package com.prestamosprima.app.ws.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prestamosprima.app.ws.io.entity.PrimaryAccountEntity;
import com.prestamosprima.app.ws.io.entity.TransactionEntity;
import com.prestamosprima.app.ws.io.entity.UserEntity;
import com.prestamosprima.app.ws.io.repositories.AccountRepository;
import com.prestamosprima.app.ws.io.repositories.TransactionRepository;
import com.prestamosprima.app.ws.io.repositories.UserRepository;
import com.prestamosprima.app.ws.service.AccountService;
import com.prestamosprima.app.ws.service.TransactionService;
import com.prestamosprima.app.ws.shared.Utils;
import com.prestamosprima.app.ws.shared.dto.AccountDto;
import com.prestamosprima.app.ws.shared.dto.TransactionDto;
import com.prestamosprima.app.ws.shared.dto.UserDto;

@Service
public class AccountServiceImpl implements AccountService{
	
	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private static int nextAccountNumber = 11223145;
	
	@Autowired 
	UserRepository userRepository;
	
	@Autowired 
	AccountRepository accountRepository;
	
	@Autowired 
	TransactionService transactionService;
	
	/**
	 * Create account
	 */
	@Override
	public PrimaryAccountEntity createAccount(UserDto userDto) {
		PrimaryAccountEntity accountEntity= new PrimaryAccountEntity();
		//if user exists and it doesn't exist account, create account
		if(userDto!=null && userDto.getAccountNumber()==null) {
			accountEntity.setAccountBalance(new BigDecimal(0.0));
			accountEntity.setAccountStatement(new BigDecimal(0.0));
			accountEntity.setAccountNumber(accountGen());
			accountEntity.setOverdraft(Utils.OVERDRAFT_STANDARD);
			UserEntity userEntity= userRepository.findByEmail(userDto.getEmail());
			accountEntity.setUser(userEntity);
			log.debug("Creating Account");
			accountEntity= accountRepository.save(accountEntity);
		}
		
		return accountEntity;
	}
	
	 private int accountGen() {
	        return ++nextAccountNumber;
	 }
	 
	 @Override
	 public AccountDto getAccount(Integer accountNumber) {
		 log.debug("Getting Account");
		 AccountDto accountDto= new AccountDto();
		 PrimaryAccountEntity accountEntity= accountRepository.findByAccountNumber(accountNumber);
		 if (accountEntity==null)
			 throw new RuntimeException(accountNumber.toString());
		 
		 BeanUtils.copyProperties(accountEntity, accountDto);
		 accountDto.setUserId(accountEntity.getUser().getUserId());
		 return accountDto;
	 }

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
	public TransactionDto deposit(Integer accountNumber, BigDecimal amount, String description) {
		TransactionDto transactionDtoResult= null;
		//Get the Account
		PrimaryAccountEntity accountEntity= accountRepository.findByAccountNumber(accountNumber);
		if (accountEntity!=null) {
			log.debug("Deposit amount");
			//update balance
			BigDecimal newAccountBalance= accountEntity.getAccountBalance().add(amount);
			accountEntity.setAccountBalance(newAccountBalance);
			
			//update statement
			//TODO
			
			log.debug("create new transaction");
			//Create Transaction
			Date date = new Date();
			TransactionDto newTransactionDto= new TransactionDto(date, description, amount, accountEntity);
			transactionDtoResult= transactionService.createTransaction(newTransactionDto);
	
		}
		return transactionDtoResult;
	}
	
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
	public TransactionDto withdraw(Integer accountNumber, BigDecimal amount, String description) {
		TransactionDto transactionDtoResult= null;
		//Get the Account
		PrimaryAccountEntity accountEntity= accountRepository.findByAccountNumber(accountNumber);
		if (accountEntity!=null) {
			//check the withdrawal limit
			BigDecimal currentBalance= accountEntity.getAccountBalance();
			BigDecimal overdraft= accountEntity.getOverdraft();
			if(isWithdrawalAllowed(currentBalance, overdraft, amount)) {
				log.debug("withdraw amount");
				//update balance
				BigDecimal newAccountBalance= accountEntity.getAccountBalance().subtract(amount);
				
				log.debug("create new transaction");
				//Create Transaction
				Date date = new Date();
				BigDecimal negativeAmount= amount.negate();
				TransactionDto newTransactionDto= new TransactionDto(date, description, negativeAmount, accountEntity);
				transactionDtoResult= transactionService.createTransaction(newTransactionDto);
			}
			
		}
		
		return transactionDtoResult;
	}

	private boolean isWithdrawalAllowed(BigDecimal currentBalance, BigDecimal overdraft, BigDecimal amount) {
		Boolean isWithdrawalAllowed= Math.abs(currentBalance.subtract(amount).doubleValue()) <= overdraft.doubleValue();
		return isWithdrawalAllowed;
	}

	 
}
