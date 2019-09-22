package com.prestamosprima.app.ws.service;

import com.prestamosprima.app.ws.io.entity.PrimaryAccountEntity;
import com.prestamosprima.app.ws.shared.dto.TransactionDto;
import com.prestamosprima.app.ws.shared.exception.BusinessException;

public interface TransactionService {
	TransactionDto createTransaction(TransactionDto transactionDto);
	
}
