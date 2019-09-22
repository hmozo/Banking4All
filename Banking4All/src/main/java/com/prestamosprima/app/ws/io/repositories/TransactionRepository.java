package com.prestamosprima.app.ws.io.repositories;

import org.springframework.data.repository.CrudRepository;

import com.prestamosprima.app.ws.io.entity.TransactionEntity;
import com.prestamosprima.app.ws.io.entity.UserEntity;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {

}
