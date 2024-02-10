package com.example.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.loan.pojo.RepaymentPojo;
import com.example.loan.services.RepaymentService;

@RestController
@CrossOrigin("*")
public class RepaymentController {

	@Autowired
	private RepaymentService repaymentService;

	@PostMapping("/weeklyPayment")
	public ResponseEntity<?> weeklyPayment(@RequestBody RepaymentPojo repaymentPojo) {
        return repaymentService.weeklyPayment(repaymentPojo);
	}
	
	@PostMapping("/getAllTransactions")
	public ResponseEntity<?> getAllTransactions(@RequestParam("referenceId") String referenceId) {
        return repaymentService.getAllTransactions(referenceId);
	}
}
