package com.congnitive.scale.assignment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "transaction")
public class Transaction {

	public enum TransactionType {
		CREDIT, DEBIT
	}

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@Column(name = "to_account")
	private Integer toAccount;

	@Column(name = "from_account")
	private Integer fromAccount;

	@Enumerated(EnumType.STRING)
	@Column(name = "transactionType")
	private TransactionType transactionType;

	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	private Date transactionDate;

	@Column(name = "transfer_amount")
	private double transferAmount;

	public Integer getFromAccount() {
		return fromAccount;
	}

	public Integer getId() {
		return id;
	}

	public Integer getToAccount() {
		return toAccount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public double getTransferAmount() {
		return transferAmount;
	}

	@PrePersist
	public void prePersist() {
		transactionDate = new Date();
	}

	public void setFromAccount(Integer fromAccount) {
		this.fromAccount = fromAccount;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setToAccount(Integer toAccount) {
		this.toAccount = toAccount;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}
}
