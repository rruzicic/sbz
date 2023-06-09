package com.sbz.bank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CreditRequest extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	@JsonBackReference("requestsBackReference")
	User client;

	@Column(name = "client_income", nullable = false)
	double clientIncome;

	@Column(name = "money_sum", nullable = false)
	double moneySum;

	@Column(name = "rate_number", nullable = false)
	int rateNumber;

	@Column(name = "client_employment_status", nullable = false)
	@Enumerated(EnumType.STRING)
	EmploymentStatus clientEmploymentStatus;

	@Column(name = "client_contract_start", nullable = true)
	Date clientContractStart;

	@Column(name = "client_contract_end", nullable = true)
	Date clientContractEnd;

	@Column(name = "credit_end_min", nullable = false)
	Date creditEndMin;

	@Column(name = "credit_end_max", nullable = false)
	Date creditEndMax;

	@Column(name = "credit_status", nullable = false)
	@Enumerated(EnumType.STRING)
	CreditStatus creditStatus;

	@Column(name = "recommended_approval", nullable = false)
	boolean recommendApproval;

	@Column(name= "missed_payment", nullable = false)
	boolean missedPayment;	// missed a monthly loan payment

	@Column(name = "payment_overdue", nullable = false)
	boolean paymentOverdue; // didn't pay the loan after max end
}
