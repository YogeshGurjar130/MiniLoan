package com.example.loan.entities;

import java.time.LocalDate;
import java.util.UUID;

import com.example.loan.pojo.LoanPojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long loanId;
	private double amount;
	private int term;
	private LocalDate createdDate;
	private String status;
	private LocalDate finalDate;
	@Column(unique = true)
	private String referenceId;
	@ManyToOne(fetch = FetchType.LAZY)
	private User customer;

	public Loan() {
	}

	public Loan(LoanPojo loanPojo) {
		this.amount = loanPojo.getAmount();
		this.term = loanPojo.getTerm();
		this.createdDate = loanPojo.getCreatedDate();
		this.status = loanPojo.getStatus();
	}

	@PrePersist
	void generateReferenceId() {
		referenceId = UUID.randomUUID().toString();
	}

	public long getLoanId() {
		return loanId;
	}

	public void setLoanId(long loanId) {
		this.loanId = loanId;
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

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public LocalDate getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(LocalDate finalDate) {
		this.finalDate = finalDate;
	}

}
