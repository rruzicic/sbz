package com.sbz.bookstore.model.facts;

import com.sbz.bookstore.model.Author;
import com.sbz.bookstore.model.Book;

import java.util.ArrayList;
import java.util.List;

public class RegularUserRecommendedBooks {
    private List<Book> recommendedBooks;
    private List<Author> fourMostPopularAuthors;

    public RegularUserRecommendedBooks(){
        recommendedBooks = new ArrayList<Book>();
        fourMostPopularAuthors = new ArrayList<Author>();
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
}
