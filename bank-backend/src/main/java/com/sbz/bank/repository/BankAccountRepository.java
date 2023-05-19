package com.sbz.bank.repository;

import com.sbz.bank.model.BankAccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}