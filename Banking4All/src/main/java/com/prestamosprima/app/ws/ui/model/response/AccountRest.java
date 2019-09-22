package com.prestamosprima.app.ws.ui.model.response;

import java.math.BigDecimal;

public class AccountRest {
	private Long id;
	private int accountNumber;
	private BigDecimal accountBalance;
	private BigDecimal accountStatement;

	private String userId;

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

	public BigDecimal getAccountStatement() {
		return accountStatement;
	}

	public void setAccountStatement(BigDecimal accountStatement) {
		this.accountStatement = accountStatement;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	
}
