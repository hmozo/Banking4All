package com.prestamosprima.app.ws.ui.model.request;

import java.math.BigDecimal;

import javax.validation.constraints.Positive;

public class TransactionDetailsRequestModel {
	private String userId;
	private Integer accountNumber;
	@Positive
	private BigDecimal amount;
	private String description;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	

}
