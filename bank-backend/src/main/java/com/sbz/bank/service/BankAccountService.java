package com.sbz.bank.service;

import com.sbz.bank.model.BankAccount;
import com.sbz.bank.repository.BankAccountRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {
	@Autowired
	private BankAccountRepository bankAccountRepository;

	public List<BankAccount> getAll() {
		return bankAccountRepository.findAll();
	}

	public BankAccount getById(Long id) {
		return bankAccountRepository.findById(id).get();
	}

	public BankAccount createBankAccount(BankAccount bankAccount) {
		return bankAccountRepository.save(bankAccount);
	}

	public BankAccount updateBook(BankAccount bankAccount) {
		return bankAccountRepository.save(bankAccount);
	}

	public boolean deleteBankAccount(Long id) {
		if (bankAccountRepository.existsById(id)) {
			bankAccountRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
