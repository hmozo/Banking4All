package com.prestamosprima.app.ws.shared.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.prestamosprima.app.ws.io.entity.PrimaryAccountEntity;

public class TransactionDto {
	private Long id;
	private Date date;
	private String description;
	// private String type;
	// private String status;
	private BigDecimal amount;
	// private BigDecimal availableBalance;

	private PrimaryAccountEntity account;
	
	

	public TransactionDto() {
		super();
	}

	public TransactionDto(Date date, String description, BigDecimal amount, PrimaryAccountEntity account) {
		super();
		this.date = date;
		this.description = description;
		this.amount = amount;
		this.account = account;
	}

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

	public PrimaryAccountEntity getAccount() {
		return account;
	}

	public void setAccount(PrimaryAccountEntity account) {
		this.account = account;
	}

}
