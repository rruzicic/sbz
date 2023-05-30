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
import java.util.*;
import java.util.Map.Entry;

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
	@Autowired
	private OrderService orderService;

	public List<Book> getAll() {
		return bookRepository.findAll();
	}

	public List<Book> getAllByAuthor(Author author){
		List<Book> list = new ArrayList<>();
		List<Book> allBooks = getAll();
		for (Book book: allBooks) {
			if (book.getAuthor() == author)
			{
				list.add(book);
			}
		}
		return list;
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
		userStatus.setBooksLikedBySimilarUsers(getBooksLikedBySimilarUsers(userId));

		userStatus.setTenMostPopularBooksByFourAuthors(get10MostPopularBooksBy4Authors(userId));

		userStatus.setBooksSimilarToBooksUserLikes(getBooksSimilarToBooksUserLikes(userId));

		userStatus.setInterestingBooks(getInterestingBooksForUser(userId));

		kieSession.insert(userStatus);

		//TODO Fire rules from the rules engine

		kieSession.getAgenda().getAgendaGroup("user-new").setFocus();
		kieSession.fireAllRules();
		kieSession.getAgenda().getAgendaGroup("user-choose-genres").setFocus();
		kieSession.fireAllRules();
		kieSession.getAgenda().getAgendaGroup("recommend-books").setFocus();
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

		/*if(userStatus.getHasChosenFavouriteGenres() && userStatus.getIsUserNew()){
			return getRecommendedUnauthorized();
		}*/

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

	public List<Book> getBooksLikedBySimilarUsers(long userId)
	{
		User user = userRepository.findById(userId).get();
		List<User> allUsers = userRepository.findAll();
		List<User> similarUsers = new ArrayList<User>();
		double userAverageRating = user.getAverageRating();
		for(User u: allUsers){
			if(u.getId() == user.getId())
				continue;
			double sum1 = 0;
			double sum2 = 0;
			double sum3 = 0;
			double uAvgRating = u.getAverageRating();
			for(Review r: user.getReviews()){
				double rui = u.getRatingForBook(r.getBook().getId());
				if(rui == -1)
					continue;
				sum1 += (rui - uAvgRating)*(r.getRating() - userAverageRating);
				sum2 += (rui - uAvgRating)*(rui - uAvgRating);
				sum3 += (r.getRating() - userAverageRating)*(r.getRating() - userAverageRating);
			}
			int a = 0;
			if(sum2*sum3 == 0)
				continue;
			double s = sum1/(Math.sqrt(sum2)*Math.sqrt(sum3));
			if(sum1/(Math.sqrt(sum2)*Math.sqrt(sum3)) >= 0.5)
				similarUsers.add(u);
		}
		List<Book> booksLikedBySimilarUsers = new ArrayList<>();
		for(User u: similarUsers)
		{
			for(Review r: u.getReviews())
			{
				if(r.getRating()>=4 && !booksLikedBySimilarUsers.contains(r.getBook()))
					booksLikedBySimilarUsers.add(r.getBook());
			}
		}
		return booksLikedBySimilarUsers;
	}

	public boolean doesAuthorBelongToGenre(Author author, Genre genre) {
		int sumAll = 0;
		List<Book> allBooksByAuthor = getAllByAuthor(author);
		sumAll = allBooksByAuthor.size();
		int countSpecificGenre = 0;
		for (Book book: allBooksByAuthor) {
			if (book.getGenre() == genre){
				countSpecificGenre++;
			}
		}
		double genreRatio = (double)countSpecificGenre / (double)sumAll;
		if (genreRatio >= 30.0/100.0){
			return true;
		}
		else{
			return false;
		}
	}

	public int getAuthorPopularity(Author author){
		List<Book> allBooksByAuthor = getAllByAuthor(author);
		int popularityScore = 0;
		for (Book book: allBooksByAuthor) {
			popularityScore += book.getReviews().size();
		}
		return popularityScore;
	}

	public List<Author> sortAuthorsByPopularity(List<Author> eligibleAuthors){
		Map<Author, Integer> authorPopularityMap = new HashMap<Author, Integer>();
		for (Author author: eligibleAuthors) {
			authorPopularityMap.put(author, getAuthorPopularity(author));
		}
		List<Entry<Author, Integer>> nlist = new ArrayList<>(authorPopularityMap.entrySet());
		nlist.sort(Entry.comparingByValue(Comparator.reverseOrder()));

		List<Author> sortedList = new ArrayList<>();
		for (Entry<Author, Integer> entry: nlist){
			sortedList.add(entry.getKey());
		}
		return sortedList;
	}

	public List<Author> getFourMostPopularAuthors(long userId){
		User user = userRepository.findById(userId).get();
		List<Author> eligibleAuthors = new ArrayList<>();
		List<Author> allAuthors = authorRepository.findAll();
		for (Author author: allAuthors) {
			for (Genre genre : user.getFavouriteGenres()) {
				if (doesAuthorBelongToGenre(author, genre)){
					eligibleAuthors.add(author);
				}
			}
		}
		List<Author> sortedAuthors = sortAuthorsByPopularity(eligibleAuthors);
		if (sortedAuthors.size()>=4){
			return sortedAuthors.subList(0,4);
		}
		else{ //just in case there are not even 4 authors in the list
			return sortedAuthors;
		}
	}

	public List<Book> get10MostPopularBooksBy4Authors(long userId){
		List<Author> fourAuthors = getFourMostPopularAuthors(userId);
		List<Book> allBooks = new ArrayList<>();
		for (Author author: fourAuthors){
			allBooks.addAll(getAllByAuthor(author));
		}
		allBooks.sort(Comparator.comparingDouble(Book::getAverageRating).reversed());
		if (allBooks.size() >= 10) {
			return allBooks.subList(0, 10);
		}
		else { //Just in case there is not even 10 books in the list.
			return allBooks;
		}
	}

	public List<Book> getBooksSimilarToBooksUserLikes(long userId){
		User user = userRepository.findById(userId).get();
		List<Book> books = bookRepository.findAll();
		List<Book> similarBooks = new ArrayList<>();
		for(Book book1: user.getBooksUserLikes())
		{
			for(Book book2: books){
				if(book1.getId() == book2.getId())
					continue;
				if(areBooksSimilar(book1, book2) && !similarBooks.contains(book2))
					similarBooks.add(book2);
			}
		}
		return similarBooks;
	}

	public boolean areBooksSimilar(Book b1, Book b2)
	{
		int numberOfReviewers = 0;
		int numberOfSameRatings = 0;
		for(Review r: b1.getReviews()) {
			double rating = r.getUser().getRatingForBook(b2.getId());
			if (rating != -1)
			{
				numberOfReviewers ++;
				if(Math.abs(r.getRating() - rating) <= 1)
					numberOfSameRatings ++;
			}
		}
		if(numberOfReviewers == 0)
			return false;
		return (double)numberOfSameRatings/numberOfReviewers > 0.7;

	}

	public List<Book> getBoughtBooks(Long userId){
		List<Order> orders = orderService.getOrdersByUserId(userId);
		List<Book> books = new ArrayList<>();
		for (Order order:orders) {
			books.addAll(orderService.extractBooksFromOrder(order));
		}
		return books;
	}

	public List<Author> getInterestingAuthorsForUser(Long userId){
		List<Book> books = getBoughtBooks(userId);
		List<Author> interestingAuthors = new ArrayList<>();
		int count;
		for(Author author: authorRepository.findAll()) {
			count = 0;
			for (Book book : books) {
				if(book.getAuthor() == author)
					count++;
				if(count >= 3) {
					interestingAuthors.add(book.getAuthor());
					break;
				}
			}
		}
		return interestingAuthors;
	}

	public List<Genre> getInterestingGenresForUser(Long userId){
		List<Book> books = getBoughtBooks(userId);
		List<Genre> interestingGenres = new ArrayList<>();
		int totalNumOfBooks = books.size();
		int genreCount = 0;
		for(Genre genre: Genre.values()) {
			genreCount = 0;
			for (Book book : books) {
				if(book.getGenre() == genre)
					genreCount++;
			}
			if(((float)genreCount / (float)totalNumOfBooks) >= (30.0/100.0)){
				interestingGenres.add(genre);
			}
		}
		return interestingGenres;
	}

	public List<Book> getInterestingBooksForUser(Long userId){
		List<Book> allBooks = bookRepository.findAll();
		List<Author> interestingAuthors = getInterestingAuthorsForUser(userId);
		List<Genre> interestingGenres = getInterestingGenresForUser(userId);
		List<Book> interestingBooks = new ArrayList<>();
		for(Book book: allBooks){
			if(interestingAuthors.contains(book.getAuthor())){
				interestingBooks.add(book);
			}
			else if (interestingGenres.contains(book.getGenre())){
				interestingBooks.add(book);
			}
		}
		return interestingBooks;
	}
}
