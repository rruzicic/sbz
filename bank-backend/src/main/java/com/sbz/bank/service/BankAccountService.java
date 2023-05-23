package com.sbz.bank.service;

import com.sbz.bank.model.BankAccount;
import com.sbz.bank.model.User;
import com.sbz.bank.repository.BankAccountRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import net.bytebuddy.asm.Advice.Local;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {
	@Autowired
	private BankAccountRepository bankAccountRepository;
	@Autowired
	private UserService userService;

	public List<BankAccount> getAll() {
		return bankAccountRepository.findAll();
	}

	public List<BankAccount> getMyAccounts(Long userId) {
		return bankAccountRepository.findByOwner(userId);
	}

	public BankAccount getById(Long id) {
		return bankAccountRepository.findById(id).get();
	}

	public BankAccount createBankAccount(BankAccount bankAccount) {
		if (bankAccount.getOwner().getId() == null || bankAccount.getOwner().getId() == 0) return null;
		User owner = userService.getById(bankAccount.getOwner().getId());
		if (owner == null) return null;
		List<BankAccount> ownersAccounts = owner.getAccounts();
		if (ownersAccounts == null || ownersAccounts.size() == 0) {
			bankAccount.setCvv(100);
		}
		else {
			bankAccount.setCvv(ownersAccounts.get(ownersAccounts.size()-1).getCvv() + 1);
		}
		//bankAccount.setBalance(0);
		LocalDateTime inFiveYears = LocalDateTime.of(LocalDateTime.now().getYear() + 5, LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(), 0, 0);
		bankAccount.setCreditCardExpiryDate(inFiveYears);
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
