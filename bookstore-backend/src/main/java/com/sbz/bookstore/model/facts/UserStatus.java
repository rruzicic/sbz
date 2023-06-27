package com.sbz.bookstore.model.facts;

import com.sbz.bookstore.model.Author;
import com.sbz.bookstore.model.Book;
import com.sbz.bookstore.model.Genre;
import com.sbz.bookstore.model.User;

import com.sbz.bookstore.repository.BookRepository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

public class UserStatus {
    @Autowired
    private BookRepository bookRepository;
    private List<Book> allBooks;
    private boolean isUserNew;
    private boolean hasChosenFavouriteGenres;
    private List<Book> booksLikedBySimilarUsers;
    private List<Author> eligibleAuthors;
    private List<Book> booksByEligibleAuthors;
    private List<Book> tenMostPopularBooksByFourAuthors;
    private List<Book> booksSimilarToBooksUserLikes;
    private List<Book> boughtBooks;
    private List<Author> allAuthors;
    private List<Author> interestingAuthors;
    private List<Genre> allGenres;
    private List<Genre> interestingGenres;
    private List<Book> interestingBooks;


    public UserStatus(){
        allBooks = new ArrayList<Book>();
        isUserNew = true;
        hasChosenFavouriteGenres = false;
        booksLikedBySimilarUsers = new ArrayList<Book>();
        eligibleAuthors = new ArrayList<Author>();
        booksByEligibleAuthors = new ArrayList<Book>();
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

    public List<Book> getAllBooks(){
        return allBooks;
    }
    public void setAllBooks(List<Book> allBooks) {
        this.allBooks = allBooks;
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

    public List<Author> getEligibleAuthors() {
        return eligibleAuthors;
    }

    public void setEligibleAuthors(List<Author> eligibleAuthors) {
        this.eligibleAuthors = eligibleAuthors;
    }

    public void addToEligibleAuthors(Author author){
        this.eligibleAuthors.add(author);
    }

    public List<Book> getBooksByEligibleAuthors() {
        return booksByEligibleAuthors;
    }

    public void setBooksByEligibleAuthors(List<Book> booksByEligibleAuthors) {
        this.booksByEligibleAuthors = booksByEligibleAuthors;
    }

    public void addToBooksByEligibleAuthors(Book book){
        this.booksByEligibleAuthors.add(book);
    }

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

    public List<Book> getAllByAuthor(Author author){
        List<Book> list = new ArrayList<>();
        List<Book> allBooks = getAllBooks();
        for (Book book: allBooks) {
            if (book.getAuthor() == author)
            {
                list.add(book);
            }
        }
        return list;
    }
    public int getAuthorPopularity(Author author){
        List<Book> allBooksByAuthor = getAllByAuthor(author);
        int popularityScore = 0;
        for (Book book: allBooksByAuthor) {
            popularityScore += book.getReviews().size();
        }
        return popularityScore;
    }
    public List<Author> sortAuthorsByPopularity2(List<Author> eligibleAuthors){
        Map<Author, Integer> authorPopularityMap = new HashMap<Author, Integer>();
        for (Author author: eligibleAuthors) {
            authorPopularityMap.put(author, getAuthorPopularity(author));
        }
        List<Map.Entry<Author, Integer>> nlist = new ArrayList<>(authorPopularityMap.entrySet());
        nlist.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<Author> sortedList = new ArrayList<>();
        for (Map.Entry<Author, Integer> entry: nlist){
            sortedList.add(entry.getKey());
        }
        if (sortedList.size()>=4){
            return sortedList.subList(0,4);
        }
        else{ //just in case there are not even 4 authors in the list
            return sortedList;
        }
    }

    public List<Book> sortBooksByPopularity2(List<Book> books){
        books.sort(Comparator.comparingDouble(Book::getAverageRating).reversed());
        if (books.size() >= 10) {
            return books.subList(0, 10);
        }
        else { //Just in case there is not even 10 books in the list.
            return books;
        }
    }

    public boolean isEligibleAuthor(Author a)
    {
        for(Author author: eligibleAuthors)
        {
            if(author.getId()==a.getId())
                return true;
        }
        return false;
    }
}
