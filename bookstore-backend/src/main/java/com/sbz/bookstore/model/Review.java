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
	@JsonBackReference("userBackReference")
	User user;

	@ManyToOne
	@JoinColumn(name = "book_id", nullable = false)
	@JsonBackReference("bookBackReference")
	Book book;

	@Column
	double rating;

	public boolean checkRatingSimilarity(double rating1, double rating2)
	{
		if(rating1==-1 || rating2==-1)
			return false;
		if(Math.abs(rating1 - rating2) <= 1)
			return true;
		return false;
	}
}
