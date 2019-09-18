package com.prestamosprima.app.ws;

import org.springframework.data.repository.CrudRepository;

import com.prestamosprima.app.ws.io.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);
}
