package com.sbz.bookstore.model;

import com.fasterxml.jackson.annotation.JsonInclude;
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
import javax.persistence.Transient;

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
	int numberOfCopies;

	@Column
	LocalDate addedToBookstoreDate;

	@Column
	double price;

	@Enumerated(EnumType.STRING)
	Genre genre;

	@OneToMany(mappedBy = "book", orphanRemoval = true)
	@JsonManagedReference("bookBackReference")
	List<Review> reviews;

	@JsonInclude
	@Transient
	boolean new_;
	@JsonInclude
	@Transient
	boolean isPopular;
	@JsonInclude
	@Transient
	RatingLevel rating;

	@JsonInclude
	@Transient
	int recommendationPoints = 0;
	@Transient
	public double getAverageRating() {
		if (reviews.size() == 0) return 0;
		double ratingSum = 0;
		for (Review review: reviews) {
			ratingSum += review.getRating();
		}
		return ratingSum / reviews.size();
	}

	@Transient
	public void incrementRecommendationPoints(int inc){
		recommendationPoints += inc;
	}

	public boolean isBookNew()
	{
		return LocalDate.now().plusMonths(-1).compareTo(addedToBookstoreDate) < 0 || LocalDate.now().plusMonths(-6).compareTo(publishDate) < 0 ;
	}
}
