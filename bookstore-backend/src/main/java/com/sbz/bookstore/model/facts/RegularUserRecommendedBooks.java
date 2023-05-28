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
        for(Book b: recommendedBooks)
        {
            if(b.getId() == item.getId())
            {
                b.incrementRecommendationPoints();
                return;
            }
        }
        item.incrementRecommendationPoints();
        recommendedBooks.add(item);
    }

    public void removeBook(Book book)
    {
        System.out.println("---------");
        for(Book b: recommendedBooks)
        {
            if(b.getId() == book.getId())
            {
                recommendedBooks.remove(b);

                //System.out.println();
                break;
            }
        }
    }
}
