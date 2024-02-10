package com.example.loan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.loan.entities.Loan;
import com.example.loan.entities.User;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

	List<Loan> findAllByCustomer(User customer);

	List<Loan> findAllByStatus(String status);

	Loan findAllByReferenceId(String referenceId);

}
