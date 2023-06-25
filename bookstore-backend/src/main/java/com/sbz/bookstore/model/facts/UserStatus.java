package com.sbz.bookstore.model.facts;

import com.sbz.bookstore.model.Author;
import com.sbz.bookstore.model.Book;
import com.sbz.bookstore.model.Genre;
import com.sbz.bookstore.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserStatus {
    private boolean isUserNew;
    private boolean hasChosenFavouriteGenres;
    private List<Book> booksLikedBySimilarUsers;
    private List<Book> tenMostPopularBooksByFourAuthors;
    private List<Book> booksSimilarToBooksUserLikes;
    private List<Book> boughtBooks;
    private List<Author> interestingAuthors;
    private List<Genre> interestingGenres;
    private List<Book> interestingBooks;


    public UserStatus(){
        isUserNew = true;
        hasChosenFavouriteGenres = false;
        booksLikedBySimilarUsers = new ArrayList<Book>();
        tenMostPopularBooksByFourAuthors = new ArrayList<Book>();
        booksSimilarToBooksUserLikes = new ArrayList<Book>();
        boughtBooks = new ArrayList<Book>();
        interestingAuthors = new ArrayList<Author>();
        interestingGenres = new ArrayList<Genre>();
        interestingBooks = new ArrayList<Book>();
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



    public List<Book> getBoughtBooks() {
        return boughtBooks;
    }

    public void setBoughtBooks(List<Book> boughtBooks) {
        this.boughtBooks = boughtBooks;
    }


    public List<Book> getInterestingBooks() {
        return interestingBooks;
    }

    public void setInterestingBooks(List<Book> interestingBooks) {
        this.interestingBooks = interestingBooks;
    }

    public List<Author> getInterestingAuthors() {
        return interestingAuthors;
    }

    public void setInterestingAuthors(List<Author> interestingAuthors) {
        this.interestingAuthors = interestingAuthors;
    }

    public List<Genre> getInterestingGenres() {
        return interestingGenres;
    }

    public void setInterestingGenres(List<Genre> interestingGenres) {
        this.interestingGenres = interestingGenres;
    }
}
