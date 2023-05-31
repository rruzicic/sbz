package com.sbz.bookstore.model.facts;

import com.sbz.bookstore.model.Author;
import com.sbz.bookstore.model.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RegularUserRecommendedBooks {
    private List<Book> recommendedBooks;

    public RegularUserRecommendedBooks(){
        recommendedBooks = new ArrayList<Book>();
    }

    public List<Book> getRecommendedBooks() {
        return recommendedBooks;
    }

    public void setRecommendedBooks(final List<Book> recommendedBooks) {
        this.recommendedBooks = recommendedBooks;
    }

    public void addToRecommended(Book item) {
        for(Book b: recommendedBooks)
        {
            if(b.getId() == item.getId())
            {
                b.incrementRecommendationPoints(2);
                return;
            }
        }
        item.incrementRecommendationPoints(2);
        if(item.isBookNew())
            item.incrementRecommendationPoints(1);
        recommendedBooks.add(item);
    }


    public void sortByRecommendationPoints(){
        recommendedBooks.sort(Comparator.comparingInt(Book::getRecommendationPoints).reversed());
        if(recommendedBooks.size() > 20)
            setRecommendedBooks(recommendedBooks.subList(0,20));
    }


}
