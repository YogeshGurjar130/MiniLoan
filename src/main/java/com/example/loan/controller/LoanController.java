package com.example.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.loan.pojo.LoanPojo;
import com.example.loan.services.LoanService;

@RestController
@CrossOrigin("*")
public class LoanController {

	@Autowired
	private LoanService loanService;
	
	@PostMapping("/loanApply")
	public ResponseEntity<String> loanApply(@RequestBody LoanPojo loanPojo) {
        return loanService.applyForLoan(loanPojo);
	}

	@GetMapping("/getCustomersLoan")
	public ResponseEntity<?> getCustomersLoan(@RequestParam("email") String email) {
		return loanService.getLoanOfCustomer(email);
	}
	
	@GetMapping("/getAllPendingLoan")
	public ResponseEntity<?> getAllPendingLoan() {
		return loanService.getAllPendingLoan();
	}
	
	@PostMapping("/updateLoanStatus")
	public ResponseEntity<String> updateLoanStatus(@RequestParam("referenceId") String referenceId) {
		 return loanService.updateStatus(referenceId);
	}
}
