package com.prestamosprima.app.ws.io.repositories;

import org.springframework.data.repository.CrudRepository;

import com.prestamosprima.app.ws.io.entity.PrimaryAccountEntity;
import com.prestamosprima.app.ws.io.entity.UserEntity;

public interface AccountRepository extends CrudRepository<PrimaryAccountEntity, Long>{
	PrimaryAccountEntity findByAccountNumber(Integer accountNumber);
}
