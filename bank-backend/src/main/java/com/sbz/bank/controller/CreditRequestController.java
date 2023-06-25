package com.sbz.bank.controller;

import com.sbz.bank.dto.CreditRequestDTO;
import com.sbz.bank.model.BankAccount;
import com.sbz.bank.model.CreditRequest;
import com.sbz.bank.model.CreditStatus;
import com.sbz.bank.model.CustomUserDetails;
import com.sbz.bank.model.User;
import com.sbz.bank.service.BankAccountService;
import com.sbz.bank.service.CreditRequestService;

import java.util.List;

import com.sbz.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
	@Autowired
	private UserService userService;

	@GetMapping("/all")
	public ResponseEntity<List<CreditRequest>> getAll() {
		return ResponseEntity.ok(creditRequestService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CreditRequest> getById(@PathVariable Long id) {
		return ResponseEntity.ok(creditRequestService.getById(id));
	}

	@PostMapping("/new")
	public ResponseEntity<CreditRequest> createCreditRequest(@RequestBody CreditRequestDTO creditRequestDTO) {
		User user = userService.getById(((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
		CreditRequest creditRequest = new CreditRequest(
				user,
				creditRequestDTO.getClientIncome(),
				creditRequestDTO.getMoneySum(),
				creditRequestDTO.getRateNumber(),
				creditRequestDTO.getClientEmploymentStatus(),
				creditRequestDTO.getClientContractStart(),
				creditRequestDTO.getClientContractEnd(),
				creditRequestDTO.getCreditEndMin(),
				creditRequestDTO.getCreditEndMax(),
				CreditStatus.PENDING,
				false
		);

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
