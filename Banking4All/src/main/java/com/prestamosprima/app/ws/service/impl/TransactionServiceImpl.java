package com.prestamosprima.app.ws.service.impl;

import java.util.Date;

import org.apache.catalina.connector.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prestamosprima.app.ws.io.entity.PrimaryAccountEntity;
import com.prestamosprima.app.ws.io.entity.TransactionEntity;
import com.prestamosprima.app.ws.io.repositories.AccountRepository;
import com.prestamosprima.app.ws.io.repositories.TransactionRepository;
import com.prestamosprima.app.ws.service.TransactionService;
import com.prestamosprima.app.ws.shared.dto.TransactionDto;
import com.prestamosprima.app.ws.shared.exception.BusinessException;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired 
	TransactionRepository transactionRepository;
	
	@Override
	public TransactionDto createTransaction(TransactionDto transactionDto)  {
		TransactionEntity transactionEntity= new TransactionEntity();
		BeanUtils.copyProperties(transactionDto, transactionEntity);
		TransactionEntity transactionEntityStored= transactionRepository.save(transactionEntity);
		
		if(transactionEntityStored==null) {
			throw new BusinessException(Response.SC_INTERNAL_SERVER_ERROR, "Transaction not created");
		}
		
		BeanUtils.copyProperties(transactionEntityStored, transactionDto);
		transactionDto.getAccount().getTransactions().add(transactionEntity);
		
		return transactionDto;
	}

}
