package com.sbz.bookstore.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	String name;

	@Column
	String password;

	@OneToMany(mappedBy = "user", orphanRemoval = true)
	@JsonManagedReference
	List<Review> reviews;

	@ElementCollection
	@Enumerated(EnumType.STRING)
	@Column(name = "genre")
	List<Genre> favouriteGenres;

	@Column
	@Enumerated(EnumType.STRING)
	Role role;

	public User(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.name = user.getName();;
		this.password = user.getPassword();
		this.reviews = user.getReviews();
		this.favouriteGenres = user.getFavouriteGenres();
		this.role = user.getRole();
	}
}
