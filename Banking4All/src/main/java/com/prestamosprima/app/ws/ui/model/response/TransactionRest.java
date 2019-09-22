package com.prestamosprima.app.ws.ui.model.response;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Positive;

import com.prestamosprima.app.ws.io.entity.PrimaryAccountEntity;

public class TransactionRest {
	private Long id;
	private Date date;
	private String description;
	// private String type;
	// private String status;
	private BigDecimal amount;
	// private BigDecimal availableBalance;

	private Integer accountNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
}

