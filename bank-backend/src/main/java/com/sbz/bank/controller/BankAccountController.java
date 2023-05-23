package com.sbz.bank.controller;

import com.sbz.bank.model.BankAccount;
import com.sbz.bank.model.CustomUserDetails;
import com.sbz.bank.model.User;
import com.sbz.bank.service.BankAccountService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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

	//@PreAuthorize("hasRole('USER')")
	@GetMapping("/my-accounts")
	public ResponseEntity<List<BankAccount>> getUserAccounts() {
		return ResponseEntity.ok(bankAccountService.getMyAccounts(((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()));
	}
	@GetMapping("/{id}")
	public ResponseEntity<BankAccount> getById(@PathVariable Long id) {
		return ResponseEntity.ok(bankAccountService.getById(id));
	}

	//@PreAuthorize("hasRole('USER')")
	@PostMapping("/new")
	public ResponseEntity<BankAccount> createBankAccount(@RequestBody BankAccount bankAccount) {
		User owner = new User();
		owner.setId(((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
		bankAccount.setOwner(owner);
		BankAccount createdBankAccount = bankAccountService.createBankAccount(bankAccount);
		return createdBankAccount != null ? ResponseEntity.ok(createdBankAccount) : ResponseEntity.badRequest().build();
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
