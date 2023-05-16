package com.sbz.bank.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="bookstore_user")
@Entity
@NoArgsConstructor
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


	public User(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.firstName = user.getFirstName();
		this.password = user.getPassword();
		this.role = user.getRole();
	}
}
