package com.sbz.bank.repository;

import com.sbz.bank.model.BankAccount;
import com.sbz.bank.model.CreditRequest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CreditRequestRepository extends JpaRepository<CreditRequest, Long> {
	@Query("select cre from CreditRequest cre where cre.client.id = ?1")
	List<CreditRequest> findByClient(Long userId);
}