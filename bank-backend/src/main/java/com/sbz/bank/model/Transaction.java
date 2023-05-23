package com.sbz.bank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
public class Transaction extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "sender_id", nullable = false)
	@JsonBackReference("outboundTransactionBackReference")
	User sender;
	@ManyToOne
	@JoinColumn(name = "receiver_id", nullable = false)
	@JsonBackReference("inboundTransactionBackReference")
	User receiver;
	@Column
	int cvv;
	@Column
	LocalDateTime expiryDate;
	@Column
	double amount;
	@Embedded
	Location location;
	@Column
	LocalDateTime timeOfTransaction;
	@Column
	boolean fraudulent;
}
