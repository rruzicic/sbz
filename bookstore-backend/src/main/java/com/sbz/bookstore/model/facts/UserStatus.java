package com.sbz.bookstore.model.facts;

import com.sbz.bookstore.model.Book;
import com.sbz.bookstore.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserStatus {
    private boolean isUserNew;
    private boolean hasChosenFavouriteGenres;
    private List<Book> booksLikedBySimilarUsers;
    private List<Book> tenMostPopularBooksByFourAuthors;
    private List<Book> booksSimilarToBooksUserLikes;
    private List<Book> interestingBooks;


    public UserStatus(){
        isUserNew = true;
        hasChosenFavouriteGenres = false;
        booksLikedBySimilarUsers = new ArrayList<Book>();
        tenMostPopularBooksByFourAuthors = new ArrayList<Book>();
        booksSimilarToBooksUserLikes = new ArrayList<Book>();
        interestingBooks = new ArrayList<Book>();
    }

    public void addBookToSimilarBooks(Book book)
    {
        for(Book b: booksSimilarToBooksUserLikes)
        {
            if (b.getId() == book.getId())
                return;
        }
        booksSimilarToBooksUserLikes.add(book);
    }

    public boolean getIsUserNew(){
        return isUserNew;
    }

    public void setIsUserNew(boolean value){
        this.isUserNew = value;
    }

    public boolean getHasChosenFavouriteGenres() {return hasChosenFavouriteGenres;}

    public void setHasChosenFavouriteGenres(boolean value) {this.hasChosenFavouriteGenres = value;}

    public List<Book> getBooksLikedBySimilarUsers(){return booksLikedBySimilarUsers;}
    public void setBooksLikedBySimilarUsers(List<Book> booksLikedBySimilarUsers) {this.booksLikedBySimilarUsers = booksLikedBySimilarUsers;}

    public List<Book> getTenMostPopularBooksByFourAuthors() {
        return tenMostPopularBooksByFourAuthors;
    }

    public void setTenMostPopularBooksByFourAuthors(List<Book> tenMostPopularBooksByFourAuthors) {
        this.tenMostPopularBooksByFourAuthors = tenMostPopularBooksByFourAuthors;
    }
    public List<Book> getBooksSimilarToBooksUserLikes(){return booksSimilarToBooksUserLikes;}
    public void setBooksSimilarToBooksUserLikes(List<Book> booksSimilarToBooksUserLikes) {this.booksSimilarToBooksUserLikes = booksSimilarToBooksUserLikes;}

    public List<Book> getInterestingBooks() {
        return interestingBooks;
    }

    public void setInterestingBooks(List<Book> interestingBooks) {
        this.interestingBooks = interestingBooks;
    }
}
