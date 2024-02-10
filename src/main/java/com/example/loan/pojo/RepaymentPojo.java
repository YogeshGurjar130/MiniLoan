package com.example.loan.pojo;

import java.time.LocalDate;

import com.example.loan.entities.Repayment;

public class RepaymentPojo {

	private double repayAmount;
	private String status;
	private LocalDate scheduledDate;
	private String transactionId;
	private String loanReferenceId;
	
	public RepaymentPojo() {
	}

	public RepaymentPojo(Repayment repayment) {
		this.repayAmount = repayment.getRepayAmount();
		this.status = repayment.getStatus();
		this.scheduledDate = repayment.getScheduledDate();
		this.transactionId = repayment.getTransactionId();
		this.loanReferenceId = repayment.getLoan().getReferenceId();
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

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	public String getLoanReferenceId() {
		return loanReferenceId;
	}

	public void setLoanReferenceId(String loanReferenceId) {
		this.loanReferenceId = loanReferenceId;
	}
}
