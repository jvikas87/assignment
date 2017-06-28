package com.congnitive.scale.assignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "beneficiary")
public class Beneficiary {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	@Column(name = "account_id")
	private Integer accountId;

	@Column(name = "beneficiary_account_id")
	private Integer beneficiaryAccountId;

	public Integer getAccountId() {
		return accountId;
	}

	public Integer getBeneficiaryAccountId() {
		return beneficiaryAccountId;
	}

	public Integer getId() {
		return id;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public void setBeneficiaryAccountId(Integer beneficiaryAccountId) {
		this.beneficiaryAccountId = beneficiaryAccountId;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
