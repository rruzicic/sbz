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
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="bookstore_user")
@Entity
public class User extends BaseEntity {

	@Column
	String email;

	@Column
	String name;

	@Column
	String password;

	@OneToMany(mappedBy = "user", orphanRemoval = true)
	@JsonManagedReference("userBackReference")
	List<Review> reviews;

	@ElementCollection
	@Enumerated(EnumType.STRING)
	@Column(name = "genre")
	List<Genre> favouriteGenres;
}
