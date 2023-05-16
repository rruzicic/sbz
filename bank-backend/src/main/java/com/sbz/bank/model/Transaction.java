package com.sbz.bank.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table
@Entity
@NoArgsConstructor
public class Transaction extends BaseEntity {
	@Column
	Long senderBankAccountId;
	@Column
	Long receiverBankAccountId;
	@Column
	int cvv;
	@Column
	Date expiryDate;
	@Column
	double amount;
}
