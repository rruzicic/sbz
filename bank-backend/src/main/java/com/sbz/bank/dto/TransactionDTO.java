package com.sbz.bank.dto;

import com.sbz.bank.model.Location;
import com.sbz.bank.model.Transaction;
import com.sbz.bank.model.User;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionDTO {

	public TransactionDTO() {

	}
	public TransactionDTO(long receiverId, int cvv, LocalDateTime expiryDate, double amount, Location location) {
		this.receiverId = receiverId;
		this.cvv = cvv;
		this.expiryDate = expiryDate;
		this.amount = amount;
		this.location = location;
	}
	@NotNull
	@Min(1)
	Long receiverId;
	@Min(100)
	int cvv;
	@NotNull
	LocalDateTime expiryDate;
	@Min(0)
	double amount;
	@NotNull
	Location location;

	public Transaction transactionDtoToTransaction() {
		User receiver = new User();
		receiver.setId(this.receiverId);

		Transaction transaction = new Transaction();
		transaction.setAmount(this.amount);
		transaction.setCvv(this.cvv);
		transaction.setExpiryDate(this.expiryDate);
		transaction.setLocation(this.location);
		transaction.setReceiver(receiver);
		return transaction;
	}
}
