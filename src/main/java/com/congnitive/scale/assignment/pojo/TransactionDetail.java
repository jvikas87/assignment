package com.congnitive.scale.assignment.pojo;

public class TransactionDetail {

	private Integer beneficiaryId;

	private double amount;

	public double getAmount() {
		return amount;
	}

	public Integer getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setBeneficiaryId(Integer beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

}
