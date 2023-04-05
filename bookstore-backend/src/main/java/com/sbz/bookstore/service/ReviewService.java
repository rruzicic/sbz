package com.sbz.bookstore.service;

import com.sbz.bookstore.model.Review;
import com.sbz.bookstore.repository.ReviewRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	public List<Review> getAll() {
		return reviewRepository.findAll();
	}

	public Review getById(Long id) {
		return reviewRepository.findById(id).get();
	}

	public Review createReview(Review review) {
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
