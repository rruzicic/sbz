package com.sbz.bookstore.model.facts;

import com.sbz.bookstore.model.Book;
import com.sbz.bookstore.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserStatus {
    private boolean isUserNew;
    private boolean hasChosenFavouriteGenres;

    private List<Book> booksLikedBySimilarUsers;

    public UserStatus(){
        isUserNew = true;
        hasChosenFavouriteGenres = false;
        booksLikedBySimilarUsers = new ArrayList<Book>();
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
}
