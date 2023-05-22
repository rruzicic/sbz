package com.sbz.bank.controller;

import com.sbz.bank.model.CreditRequest;
import com.sbz.bank.model.Transaction;
import com.sbz.bank.service.CreditRequestService;
import com.sbz.bank.service.TransactionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;

	@GetMapping("/all")
	public ResponseEntity<List<Transaction>> getAll() {
		return ResponseEntity.ok(transactionService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Transaction> getById(@PathVariable Long id) {
		return ResponseEntity.ok(transactionService.getById(id));
	}

	@PostMapping("/new")
	public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
		return transactionService.createTransaction(transaction) != null ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
	}

	@PostMapping("/update")
	public ResponseEntity<Transaction> update(@RequestBody Transaction transaction) {
		return ResponseEntity.ok(transactionService.updateTransaction(transaction));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return transactionService.deleteTransaction(id) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
	}
}
