package com.sbz.bank.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="bank_user")
@Entity
@NoArgsConstructor
//@ToString(callSuper = true)
public class User extends BaseEntity {
	@Column
	String email;
	@Column
	String firstName;

	@Column
	String lastName;
	@Column
	String password;
	@Column
	@Enumerated(EnumType.STRING)
	Role role;

	@Column
	@OneToMany(mappedBy = "owner", orphanRemoval = true)
	@JsonManagedReference("userBackReference")
	List<BankAccount> accounts;

	@Column
	@OneToMany(mappedBy = "submitter", orphanRemoval = true)
	@JsonManagedReference("requestsBackReference")
	List<CreditRequest> creditRequests;

	@Column
	@OneToMany(mappedBy = "sender", orphanRemoval = true)
	@JsonManagedReference("outboundTransactionBackReference")
	List<Transaction> outboundTransaction;

	@Column
	@OneToMany(mappedBy = "receiver", orphanRemoval = true)
	@JsonManagedReference("inboundTransactionBackReference")
	List<Transaction> inboundTransactions;

	public double getOutboundTransactionsAmountAverage() {
		if (this.getOutboundTransaction() == null || this.getOutboundTransaction().size() == 0) return 0;
		return this.getOutboundTransaction().stream().mapToDouble(a -> a.getAmount()).average().getAsDouble();
	}

	public User(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.firstName = user.getFirstName();
		this.password = user.getPassword();
		this.role = user.getRole();
	}
}
