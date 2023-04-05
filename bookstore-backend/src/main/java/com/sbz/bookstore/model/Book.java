package com.sbz.bookstore.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="book")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book extends BaseEntity {

	@Column
	String name;

	@OneToOne
	@JoinColumn(name = "author_id", referencedColumnName = "id")
	Author author;

	@Column
	LocalDate publishDate;

	@Column
	LocalDate addedToBookstoreDate;

	@Column
	double price;

	@Enumerated(EnumType.STRING)
	Genre genre;

	@OneToMany(mappedBy = "book", orphanRemoval = true)
	@JsonManagedReference
	List<Review> reviews;
}
