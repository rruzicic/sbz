package com.sbz.bank.repository;

import com.sbz.bank.model.CreditRequest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRequestRepository extends JpaRepository<CreditRequest, Long> {
}