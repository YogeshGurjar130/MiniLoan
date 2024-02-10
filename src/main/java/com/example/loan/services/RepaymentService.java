package com.example.loan.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.loan.entities.Loan;
import com.example.loan.entities.Repayment;
import com.example.loan.pojo.RepaymentPojo;
import com.example.loan.repository.LoanRepository;
import com.example.loan.repository.RepaymentRepository;
import com.example.loan.utils.ResponseMessageUtil;
import com.example.loan.utils.StatusUtil;

@Service
public class RepaymentService {

	@Autowired
	private RepaymentRepository repaymentRepository;
	@Autowired
	private LoanRepository loanRepository;

	public ResponseEntity<String> weeklyPayment(RepaymentPojo repaymentPojo) {
		Repayment repayment = null;
		Loan loan = null;
		try {
			loan = loanRepository.findAllByReferenceId(repaymentPojo.getLoanReferenceId());
			if (loan == null) {
				return ResponseEntity.ok(ResponseMessageUtil.LOAN_NOT_FOUND);
			} else {
				repayment = repaymentRepository.findByTransactionId(repaymentPojo.getTransactionId());
				if (repayment == null) {
					return ResponseEntity.ok(ResponseMessageUtil.TRANSACTION_NOT_FOUND);
				} else {
					repayment.setStatus(StatusUtil.PAID);
					repaymentRepository.save(repayment);
					if (repayment.getScheduledDate().equals(loan.getFinalDate())) {
						loan.setStatus(StatusUtil.PAID);
						loanRepository.save(loan);
						return ResponseEntity.ok(ResponseMessageUtil.LAST_TRANSACTION_SUCCESSFULLY);
					} else {
						return ResponseEntity.ok(ResponseMessageUtil.TRANSACTION_SUCCESSFULLY);
					}
				}
			}
		} catch (Exception e) {
			return ResponseEntity.ok(ResponseMessageUtil.SOMETHING_WENT_WRONG);
		}

	}

	public ResponseEntity<?> getAllTransactions(String referenceId) {
		List<RepaymentPojo> transactionPojoList = null;
		List<Repayment> transactions = null;
		Loan loan = null;
		try {
			loan = loanRepository.findAllByReferenceId(referenceId);
			if (loan == null) {
				return ResponseEntity.ok(ResponseMessageUtil.LOAN_NOT_FOUND);
			} else {
				transactions = repaymentRepository.findAllByLoan(loan);
				if (transactions == null) {
					return ResponseEntity.ok(ResponseMessageUtil.TRANSACTION_NOT_FOUND);
				} else {
					transactionPojoList = new ArrayList<>();
					for (Repayment repayment : transactions) {
						RepaymentPojo repaymentPojo = new RepaymentPojo(repayment);
						transactionPojoList.add(repaymentPojo);
					}
					return ResponseEntity.ok(transactionPojoList);
				}
			}
		} catch (Exception e) {
			return ResponseEntity.ok(ResponseMessageUtil.SOMETHING_WENT_WRONG);
		}
	}

}
