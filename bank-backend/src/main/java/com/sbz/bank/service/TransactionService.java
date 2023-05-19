package com.sbz.bank.service;

import com.sbz.bank.model.CreditRequest;
import com.sbz.bank.model.Transaction;
import com.sbz.bank.repository.CreditRequestRepository;
import com.sbz.bank.repository.TransactionRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;

	public List<Transaction> getAll() {
		return transactionRepository.findAll();
	}

	public Transaction getById(Long id) {
		return transactionRepository.findById(id).get();
	}

	public Transaction createTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	public Transaction updateTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	public boolean deleteTransaction(Long id) {
		if (transactionRepository.existsById(id)) {
			transactionRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
