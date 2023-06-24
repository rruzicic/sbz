package com.sbz.bank.repository;

import com.sbz.bank.model.BankAccount;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
	@Query("select acc from BankAccount acc where acc.owner.id = ?1")
	List<BankAccount> findByOwner(Long userId);
}