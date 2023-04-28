package com.sbz.bookstore.model.facts;

import com.sbz.bookstore.model.Book;
import com.sbz.bookstore.model.RatingLevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UnauthorizedUserRecommendedBooks {
	private List<Book> recommendedBooks;
	private Random rand;

	public UnauthorizedUserRecommendedBooks() {
		rand = new Random();
		recommendedBooks = new ArrayList<Book>();
	}

	public List<Book> getRecommendedBooks() {
		return recommendedBooks;
	}

	public void setRecommendedBooks(final List<Book> recommendedBooks) {
		this.recommendedBooks = recommendedBooks;
	}

	public void addToRecommended(Book item) {
		recommendedBooks.add(item);
	}

	public void removeBadBooks() {
		for (Book book : recommendedBooks) {
			if (book.getRating() == RatingLevel.BAD) {
				recommendedBooks.remove(book);
			}
		}
	}

	public void pickTenRandomBooks() {
		int random = rand.nextInt(recommendedBooks.size());
		if (recommendedBooks.size() == 10) {
			return;
		}
		recommendedBooks.remove(random);
		pickTenRandomBooks();
	}
}
