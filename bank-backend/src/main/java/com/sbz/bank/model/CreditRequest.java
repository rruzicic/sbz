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
	@JoinColumn(name = "submitter_id", nullable = false)
	@JsonBackReference("requestsBackReference")
	User submitter;
	@Column
	double amount;
	@Column
	int loanTerm;
	@Column
	@Enumerated(EnumType.STRING)
	EmploymentStatus employmentStatus;
	@Column
	Date employmentStartDate;
	@Column
	Date temporaryContractExpiry;
	@Column
	@Enumerated(EnumType.STRING)
	CreditStatus creditStatus;
	@Column
	boolean recommendApproval;
}
