package com.sbz.bookstore.service;

import com.sbz.bookstore.config.KieConfig;
import com.sbz.bookstore.model.*;
import com.sbz.bookstore.model.facts.RegularUserRecommendedBooks;
import com.sbz.bookstore.model.facts.UnauthorizedUserRecommendedBooks;
import com.sbz.bookstore.model.facts.UserStatus;
import com.sbz.bookstore.repository.AuthorRepository;
import com.sbz.bookstore.repository.BookRepository;
import com.sbz.bookstore.repository.UserRepository;
import com.sbz.bookstore.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private UserRepository userRepository;

	public List<Book> getAll() {
		return bookRepository.findAll();
	}

	public List<Book> getRecommendedUnauthorized() {
		KieContainer kieContainer = new KieConfig().kieContainer();
		KieSession kieSession = kieContainer.newKieSession();
		List<Book> books = getAll();
		for (Book book: books) {
			kieSession.insert(book);
		}
		UnauthorizedUserRecommendedBooks recommendedBooks = new UnauthorizedUserRecommendedBooks();
		kieSession.insert(recommendedBooks);

		kieSession.getAgenda().getAgendaGroup("age").setFocus();
		kieSession.fireAllRules();
		kieSession.getAgenda().getAgendaGroup("popularity").setFocus();
		kieSession.fireAllRules();
		kieSession.getAgenda().getAgendaGroup("rating").setFocus();
		kieSession.fireAllRules();
		kieSession.getAgenda().getAgendaGroup("recommendation").setFocus();
		kieSession.fireAllRules();
		kieSession.getAgenda().getAgendaGroup("remove").setFocus();
		kieSession.fireAllRules();
		return recommendedBooks.getRecommendedBooks();
	}

	public List<Book> getRecommendedRegular(Long userId){
		KieContainer kieContainer = new KieConfig().kieContainer();
		KieSession kieSession = kieContainer.newKieSession();
		List<Book> books = getAll();
		for (Book book: books) {
			kieSession.insert(book);
		}
		User regularUser = userRepository.findById(userId).get();
		kieSession.insert(regularUser);
		RegularUserRecommendedBooks recommendedBooks = new RegularUserRecommendedBooks();
		kieSession.insert(recommendedBooks);
		UserStatus userStatus = new UserStatus();
		kieSession.insert(userStatus);

		//TODO Fire rules from the rules engine

		kieSession.getAgenda().getAgendaGroup("user-new").setFocus();
		kieSession.fireAllRules();
		kieSession.getAgenda().getAgendaGroup("user-choose-genres").setFocus();
		kieSession.fireAllRules();

		if (!userStatus.getIsUserNew()){
			System.out.println("----USER IS CHANGED TO NOT NEW----");
		} else {
			System.out.println("----USER IS NEW----");
		}

		if (!userStatus.getHasChosenFavouriteGenres()){
			System.out.println("----USER HAS NOT CHOSEN FAVOURITE GENRES----");
		} else {
			System.out.println("----USER HAS CHOSEN FAVOURITE GENRES----");
		}

		if(userStatus.getHasChosenFavouriteGenres() && userStatus.getIsUserNew()){
			return getRecommendedUnauthorized();
		}

		//ODAVDE SE NASTAVLJAJU DALJA PRAVILA

		return recommendedBooks.getRecommendedBooks();
	}

	public Book getById(Long id) {
		return bookRepository.findById(id).get();
	}

	public Book createBook(Book book) {
		if (book == null) { return null; }
		if (book.getAuthor() == null) { return null; }
		if (book.getAuthor().getId() == null) { return null; }
		if (!authorRepository.existsById(book.getAuthor().getId())) { return null; }
		Author author = authorRepository.findById(book.getAuthor().getId()).get();
		book.setAuthor(author);
		book.setAddedToBookstoreDate(LocalDate.now());
		return bookRepository.save(book);
	}

	public Book updateBook(Book book) {
		return bookRepository.save(book);
	}

	public boolean deleteBook(Long id) {
		if (bookRepository.existsById(id)) {
			bookRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
