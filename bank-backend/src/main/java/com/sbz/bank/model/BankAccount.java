package com.sbz.bank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class BankAccount extends BaseEntity {
	// BankAccount id serves as both account id and credit card id
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference("userBackReference")
	User owner;

	@Column
	double balance;

	@Column
	int cvv;

	@Column
	Date creditCardExpiryDate;
}
