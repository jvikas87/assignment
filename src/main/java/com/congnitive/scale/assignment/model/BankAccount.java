package com.congnitive.scale.assignment.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "bank_account")
public class BankAccount {

	public enum Currency {
		INR, USD
	}

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "currency")
	@Enumerated(EnumType.STRING)
	private Currency currency;

	@Column(name = "account_balance")
	private double accountBalance;

	@Transient
	private List<Beneficiary> beneficiaryList;

	public double getAccountBalance() {
		return accountBalance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public List<Beneficiary> getBeneficiaryList() {
		return beneficiaryList;
	}

	public Currency getCurrency() {
		return currency;
	}

	public Integer getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setBeneficiaryList(List<Beneficiary> beneficiaryList) {
		this.beneficiaryList = beneficiaryList;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@PrePersist
	public void prePersist(){
		id=null;
	}
	
}
