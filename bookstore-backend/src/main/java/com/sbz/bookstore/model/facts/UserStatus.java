package com.sbz.bookstore.model.facts;

public class UserStatus {
    private boolean isUserNew;

    public UserStatus(){
        isUserNew = true;
    }

    public boolean getIsUserNew(){
        return isUserNew;
    }

    public void setIsUserNew(boolean value){
        this.isUserNew = value;
    }
}
