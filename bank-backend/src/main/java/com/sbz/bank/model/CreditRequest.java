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
public class CreditRequest extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	@JsonBackReference("requestsBackReference")
	User submitter;

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

	@Column
	@Enumerated(EnumType.STRING)
	CreditStatus creditStatus;

	@Column
	boolean recommendApproval;
}
