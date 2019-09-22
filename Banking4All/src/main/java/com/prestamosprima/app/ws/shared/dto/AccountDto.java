package com.prestamosprima.app.ws.shared.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.prestamosprima.app.ws.io.entity.UserEntity;

public class AccountDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5141443378464389924L;

	private Long id;
	private Integer accountNumber;
	private BigDecimal accountBalance;
	private BigDecimal accountStatement;
	
	private String userId;



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public BigDecimal getAccountStatement() {
		return accountStatement;
	}

	public void setAccountStatement(BigDecimal accountStatement) {
		this.accountStatement = accountStatement;
	}

}
