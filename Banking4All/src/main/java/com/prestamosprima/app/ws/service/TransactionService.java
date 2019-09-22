package com.prestamosprima.app.ws.service;

import com.prestamosprima.app.ws.io.entity.PrimaryAccountEntity;
import com.prestamosprima.app.ws.shared.dto.TransactionDto;

public interface TransactionService {
	TransactionDto createTransaction(TransactionDto transactionDto);
	
}
