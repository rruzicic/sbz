package com.sbz.bank.controller;

import com.sbz.bank.model.BankAccount;
import com.sbz.bank.model.Book;
import com.sbz.bank.service.BankAccountService;
import com.sbz.bank.service.BookService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank-account")
public class BankAccountController {
	@Autowired
	private BankAccountService bankAccountService;

	@GetMapping("/all")
	public ResponseEntity<List<BankAccount>> getAll() {
		return ResponseEntity.ok(bankAccountService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<BankAccount> getById(@PathVariable Long id) {
		return ResponseEntity.ok(bankAccountService.getById(id));
	}

	@PostMapping("/new")
	public ResponseEntity<BankAccount> createBook(@RequestBody BankAccount bankAccount) {
		return bankAccountService.createBankAccount(bankAccount) != null ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
	}

	@PostMapping("/update")
	public ResponseEntity<BankAccount> update(@RequestBody BankAccount bankAccount) {
		return ResponseEntity.ok(bankAccountService.updateBook(bankAccount));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return bankAccountService.deleteBankAccount(id) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
	}
}
