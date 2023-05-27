package com.sbz.bookstore.model.facts;

import com.sbz.bookstore.model.User;

public class UserStatus {
    private boolean isUserNew;
    private boolean hasChosenFavouriteGenres;

    public UserStatus(){
        isUserNew = true;
        hasChosenFavouriteGenres = false;
    }

    public boolean getIsUserNew(){
        return isUserNew;
    }

    public void setIsUserNew(boolean value){
        this.isUserNew = value;
    }

    public boolean getHasChosenFavouriteGenres() {return hasChosenFavouriteGenres;}

    public void setHasChosenFavouriteGenres(boolean value) {this.hasChosenFavouriteGenres = value;}
}
