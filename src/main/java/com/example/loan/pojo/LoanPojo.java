package com.example.loan.pojo;

import java.time.LocalDate;

import com.example.loan.entities.Loan;

public class LoanPojo {

	private double amount;
	private int term;
	private LocalDate createdDate;
	private String status;
	private String referenceId;
	private String userEmail;

	public LoanPojo() {
	}
	
	public LoanPojo(Loan loan) {
		this.amount = loan.getAmount();
		this.term = loan.getTerm();
		this.createdDate = loan.getCreatedDate();
		this.status = loan.getStatus();
		this.referenceId = loan.getReferenceId();
		this.userEmail = loan.getCustomer().getEmail();
	}
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
