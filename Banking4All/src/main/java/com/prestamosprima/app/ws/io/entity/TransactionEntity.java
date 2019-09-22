package com.prestamosprima.app.ws.io.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TransactionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8074435103495074647L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date date;
	private String description;
	// private String type;
	// private String status;
	private BigDecimal amount;
	// private BigDecimal availableBalance;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ID", nullable = false)
	private PrimaryAccountEntity account;
	
	/*public TransactionEntity(Date date, BigDecimal amount, String description, AccountEntity account) {
		super();
		this.date = date;
		this.amount = amount;
		this.description= description;
		this.account = account;
	}*/

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
