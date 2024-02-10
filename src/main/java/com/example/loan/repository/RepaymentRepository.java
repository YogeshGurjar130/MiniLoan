package com.example.loan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.loan.entities.Loan;
import com.example.loan.entities.Repayment;

@Repository
public interface RepaymentRepository extends JpaRepository<Repayment, Long> {

	Repayment findByTransactionId(String transactionId);

	List<Repayment> findAllByLoan(Loan loan);

}
