package com.sbz.bank.service;

import com.sbz.bank.config.KieConfig;
import com.sbz.bank.model.BankAccount;
import com.sbz.bank.model.CreditRequest;
import com.sbz.bank.model.Role;
import com.sbz.bank.model.Transaction;
import com.sbz.bank.model.User;
import com.sbz.bank.repository.CreditRequestRepository;
import com.sbz.bank.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private UserService userService;

	public List<Transaction> getAll() {
		return transactionRepository.findAll();
	}

	public Transaction getById(Long id) {
		return transactionRepository.findById(id).get();
	}

	public Transaction createTransaction(Transaction transaction) {
		if(!isTransactionValid(transaction)) return null;
		transaction.setTimeOfTransaction(LocalDateTime.now());
		Transaction savedTransaction = transactionRepository.save(transaction);
		KieContainer kieContainer = new KieConfig().kieContainer();
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(savedTransaction);
		kieSession.fireAllRules();
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

	private boolean isTransactionValid(Transaction transaction) {
		if (!bothUsersExist(transaction.getSender().getId(), transaction.getReceiver().getId())) return false;
		User sender = userService.getById(transaction.getSender().getId());
		User receiver = userService.getById(transaction.getReceiver().getId());
		if (sender.getRole() != Role.USER || receiver.getRole() != Role.USER) return false;
		BankAccount senderAccount = getSenderAccountWithMatchingCvv(transaction, sender);
		if (senderAccount == null) return false;
		if (!updateBalances(receiver, sender, transaction.getAmount(), senderAccount)) return false;
		return true;
	}
	private boolean bothUsersExist(Long id1, Long id2) {
		return userService.exists(id1) && userService.exists(id2);
	}
	private BankAccount getSenderAccountWithMatchingCvv(Transaction transaction, User sender) {
		return sender.getAccounts().stream().filter(bankAccount ->
			bankAccount.getCvv() == transaction.getCvv() &&
				bankAccount.getBalance() >= transaction.getAmount()).findFirst().get();
	}

	private boolean updateBalances(User receiver, User sender, double transactionAmount, BankAccount senderAccount) {
		if (receiver.getAccounts() == null || receiver.getAccounts().size() == 0) return false;
		senderAccount.setBalance(senderAccount.getBalance() - transactionAmount);
		receiver.getAccounts().get(0).setBalance(receiver.getAccounts().get(0).getBalance() + transactionAmount);
		return userService.updateUser(sender) != null && userService.updateUser(receiver) != null;
	}
}