package com.sbz.bank.repository;

import com.sbz.bank.model.Transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	List<Transaction> getBySenderId(Long senderId);
}