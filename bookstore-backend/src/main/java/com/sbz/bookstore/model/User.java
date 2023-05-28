package com.sbz.bookstore.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
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
	String name;

	@Column
	String password;

	@OneToMany(mappedBy = "user", orphanRemoval = true)
	@JsonManagedReference("userBackReference")
	List<Review> reviews;

	@ElementCollection(fetch = FetchType.EAGER)
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
		//this.reviews = user.getReviews();
		this.reviews = user.getReviews();
		this.favouriteGenres = user.getFavouriteGenres();
		this.role = user.getRole();
	}

	public double getAverageRating()
	{
		double sum = 0.0;
		for(Review r: reviews)
		{
			sum += r.getRating();
		}
		return sum/reviews.size();
	}

	public double getRatingForBook(long bookId)
	{
		for(Review r: reviews)
		{
			if(r.getBook().getId() == bookId)
				return r.getRating();
		}
		return -1;
	}

	public List<Book> getBooksUserLikes(){
		List<Book> books = new ArrayList<>();
		for(Review r: reviews){
			if(r.getRating()>=4)
				books.add(r.getBook());
		}
		return books;
	}
}
