package com.example.loan.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Repayment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long repaymentId;
	private double repayAmount;
	private String status;
	private LocalDate scheduledDate;
	@Column(unique = true)
	private String transactionId;
	@ManyToOne(fetch = FetchType.LAZY)
	private Loan loan;

	public Repayment() {
	}

	@PrePersist
	void generateReferenceId() {
		transactionId = UUID.randomUUID().toString();
	}

	public long getRepaymentId() {
		return repaymentId;
	}

	public void setRepaymentId(long repaymentId) {
		this.repaymentId = repaymentId;
	}

	public double getRepayAmount() {
		return repayAmount;
	}

	public void setRepayAmount(double repayAmount) {
		this.repayAmount = repayAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(LocalDate scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}
