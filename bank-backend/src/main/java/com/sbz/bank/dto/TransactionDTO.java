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
	@NotNull
	@Min(1)
	Long senderId;
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

		User sender = new User();
		sender.setId(this.senderId);

		Transaction transaction = new Transaction();
		transaction.setAmount(this.amount);
		transaction.setCvv(this.cvv);
		transaction.setExpiryDate(this.expiryDate);
		transaction.setLocation(this.location);
		transaction.setSender(sender);
		transaction.setReceiver(receiver);
		return transaction;
	}
}
