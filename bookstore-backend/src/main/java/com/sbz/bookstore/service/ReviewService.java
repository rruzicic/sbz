package com.sbz.bookstore.service;

import com.sbz.bookstore.model.Book;
import com.sbz.bookstore.model.Review;
import com.sbz.bookstore.model.User;
import com.sbz.bookstore.repository.BookRepository;
import com.sbz.bookstore.repository.ReviewRepository;
import com.sbz.bookstore.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BookRepository bookRepository;

	public List<Review> getAll() {
		return reviewRepository.findAll();
	}

	public Review getById(Long id) {
		return reviewRepository.findById(id).get();
	}

	public Review reviewBook(Long userId, Long bookId, double rating) {
		if (!userRepository.existsById(userId)) { return null; }
		if (!bookRepository.existsById(bookId)) { return null; }
		if (rating < 1 || rating > 5) { return null; }

		User user = userRepository.getReferenceById(userId);
		Book book = bookRepository.getReferenceById(bookId);
		Review review = new Review();
		review.setBook(book);
		review.setUser(user);
		review.setRating(rating);
		return reviewRepository.save(review);
	}

	public Review updateReview(Review review) {
		return reviewRepository.save(review);
	}

	public boolean deleteBook(Long id) {
		if (reviewRepository.existsById(id)) {
			reviewRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
