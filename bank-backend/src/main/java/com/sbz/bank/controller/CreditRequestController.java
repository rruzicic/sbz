package com.sbz.bank.controller;

import com.sbz.bank.model.BankAccount;
import com.sbz.bank.model.CreditRequest;
import com.sbz.bank.service.BankAccountService;
import com.sbz.bank.service.CreditRequestService;

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
@RequestMapping("/credit")
public class CreditRequestController {
	@Autowired
	private CreditRequestService creditRequestService;

	@GetMapping("/all")
	public ResponseEntity<List<CreditRequest>> getAll() {
		return ResponseEntity.ok(creditRequestService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CreditRequest> getById(@PathVariable Long id) {
		return ResponseEntity.ok(creditRequestService.getById(id));
	}

	@PostMapping("/new")
	public ResponseEntity<CreditRequest> createCreditRequest(@RequestBody CreditRequest creditRequest) {
		return creditRequestService.createCreditRequest(creditRequest) != null ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
	}

	@PostMapping("/update")
	public ResponseEntity<CreditRequest> update(@RequestBody CreditRequest creditRequest) {
		return ResponseEntity.ok(creditRequestService.updateCreditRequest(creditRequest));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return creditRequestService.deleteCreditRequest(id) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
	}
}
