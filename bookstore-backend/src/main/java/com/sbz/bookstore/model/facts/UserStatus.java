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
    private List<Author> allAuthors;
    private List<Author> interestingAuthors;
    private List<Genre> allGenres;
    private List<Genre> interestingGenres;
    private List<Book> interestingBooks;


    public UserStatus(){
        isUserNew = true;
        hasChosenFavouriteGenres = false;
        booksLikedBySimilarUsers = new ArrayList<Book>();
        tenMostPopularBooksByFourAuthors = new ArrayList<Book>();
        booksSimilarToBooksUserLikes = new ArrayList<Book>();
        boughtBooks = new ArrayList<Book>();
        allAuthors = new ArrayList<Author>();
        interestingAuthors = new ArrayList<Author>();
        allGenres = new ArrayList<Genre>();
        allGenres.add(Genre.NOVEL);
        allGenres.add(Genre.EDUCATION);
        allGenres.add(Genre.ROMANCE);
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

    public List<Author> getAllAuthors() {
        return allAuthors;
    }

    public void setAllAuthors(List<Author> allAuthors) {
        this.allAuthors = allAuthors;
    }

    public List<Book> getInterestingBooks() {
        return interestingBooks;
    }

    public void setInterestingBooks(List<Book> interestingBooks) {
        this.interestingBooks = interestingBooks;
    }

    public void addToInterestingBooks(Book book){
        this.interestingBooks.add(book);
    }

    public List<Author> getInterestingAuthors() {
        return interestingAuthors;
    }

    public void setInterestingAuthors(List<Author> interestingAuthors) {
        this.interestingAuthors = interestingAuthors;
    }

    public void addToInterestingAuthors(Author author){
        this.interestingAuthors.add(author);
    }

    public List<Genre> getAllGenres() {
        return allGenres;
    }

    public void setAllGenres(List<Genre> allGenres) {
        this.allGenres = allGenres;
    }

    public List<Genre> getInterestingGenres() {
        return interestingGenres;
    }

    public void setInterestingGenres(List<Genre> interestingGenres) {
        this.interestingGenres = interestingGenres;
    }

    public void addToInterestingGenres(Genre genre){
        this.interestingGenres.add(genre);
    }
}
