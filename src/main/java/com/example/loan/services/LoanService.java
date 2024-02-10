package com.example.loan.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.loan.entities.Loan;
import com.example.loan.entities.Repayment;
import com.example.loan.entities.User;
import com.example.loan.pojo.LoanPojo;
import com.example.loan.repository.LoanRepository;
import com.example.loan.repository.RepaymentRepository;
import com.example.loan.repository.UserRepository;
import com.example.loan.utils.ResponseMessageUtil;
import com.example.loan.utils.StatusUtil;

@Service
public class LoanService {

	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RepaymentRepository repaymentRepository;

	public ResponseEntity<String> applyForLoan(LoanPojo loanPojo) {
		Loan loan = null;
		User customer = null;
		List<Repayment> repayments = null;
		try {
			customer = userRepository.findByEmail(loanPojo.getUserEmail());
			if (customer == null) {
				return ResponseEntity.ok(ResponseMessageUtil.CUSTOMER_NOT_FOUND);
			} else {
				repayments = new ArrayList<>();
				loan = new Loan(loanPojo);
				loan.setCustomer(customer);
				loan.setStatus(StatusUtil.REQUESTED);

				loanRepository.save(loan);

				double repayAmt = loan.getAmount() / loan.getTerm();

				for (int i = 1; i <= loan.getTerm(); i++) {
					LocalDate sheduledDate = loan.getCreatedDate().plusDays(i * 7);
					Repayment repayment = new Repayment();
					repayment.setRepayAmount(repayAmt);
					repayment.setScheduledDate(sheduledDate);
					repayment.setStatus(StatusUtil.PENDING);
					repayment.setLoan(loan);
					repayments.add(repayment);
					// add last due date
					if (i == loan.getTerm()) {
						loan.setFinalDate(sheduledDate);
						loanRepository.save(loan);
					}
				}

				repaymentRepository.saveAll(repayments);

				return ResponseEntity.ok(ResponseMessageUtil.LOAN_APPLIED);
			}
		} catch (Exception e) {
			return ResponseEntity.ok(ResponseMessageUtil.SOMETHING_WENT_WRONG);

		}
	}

	public ResponseEntity<?> getLoanOfCustomer(String email) {
		List<LoanPojo> loanPojoList = null;
		List<Loan> loanList = null;
		User customer = null;
		try {
			customer = userRepository.findByEmail(email);
			if (customer == null) {
				return ResponseEntity.ok(ResponseMessageUtil.CUSTOMER_NOT_FOUND);
			} else {
				loanList = loanRepository.findAllByCustomer(customer);
				if (loanList == null) {
					return ResponseEntity.ok(ResponseMessageUtil.NO_LOAN_FOUND);
				} else {
					loanPojoList = new ArrayList<>();
					for (Loan loan : loanList) {
						LoanPojo loanPojo = new LoanPojo(loan);
						loanPojoList.add(loanPojo);
					}
					return ResponseEntity.ok(loanPojoList);
				}
			}
		} catch (Exception e) {
			return ResponseEntity.ok(ResponseMessageUtil.SOMETHING_WENT_WRONG);
		}
	}

	public ResponseEntity<?> getAllPendingLoan() {
		List<LoanPojo> pendingLoanPojoList = null;
		List<Loan> pendingLoanList = null;
		try {
			pendingLoanList = loanRepository.findAllByStatus(StatusUtil.REQUESTED);
			if (pendingLoanList == null) {
				return ResponseEntity.ok(ResponseMessageUtil.NO_LOAN_PENDING);
			} else {
				pendingLoanPojoList = new ArrayList<>();
				for (Loan loan : pendingLoanList) {
					LoanPojo loanPojo = new LoanPojo(loan);
					pendingLoanPojoList.add(loanPojo);
				}
				return ResponseEntity.ok(pendingLoanPojoList);
			}
		} catch (Exception e) {
			return ResponseEntity.ok(ResponseMessageUtil.SOMETHING_WENT_WRONG);
		}
	}

	public ResponseEntity<String> updateStatus(String referenceId) {
		Loan loan = null;
		try {
			loan = loanRepository.findAllByReferenceId(referenceId);
			if (loan == null) {
				return ResponseEntity.ok(ResponseMessageUtil.NO_LOAN_FOUND);
			} else {
				loan.setStatus(StatusUtil.APPROVED);
				loanRepository.save(loan);
				return ResponseEntity.ok(ResponseMessageUtil.LOAN_APPROVED);
			}
		} catch (Exception e) {
			return ResponseEntity.ok(ResponseMessageUtil.SOMETHING_WENT_WRONG);
		}
	}

}
