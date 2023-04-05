package com.sbz.bookstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name="review")
public class Review extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference
	User user;

	@ManyToOne
	@JoinColumn(name = "book_id", nullable = false)
	@JsonBackReference
	Book book;

	@Column
	double rating;
}
