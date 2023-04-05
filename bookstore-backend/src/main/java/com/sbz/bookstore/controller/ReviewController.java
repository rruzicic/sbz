package com.sbz.bookstore.controller;

import com.sbz.bookstore.model.Review;
import com.sbz.bookstore.service.ReviewService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@GetMapping("/all")
	public ResponseEntity<List<Review>> getAll() {
		return ResponseEntity.ok(reviewService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Review> getById(@PathVariable Long id) {
		return ResponseEntity.ok(reviewService.getById(id));
	}

	@PostMapping("/new")
	public ResponseEntity<Review> createBook(@RequestBody Review review) {
		return ResponseEntity.ok(reviewService.createReview(review));
	}

	@PostMapping("/update")
	public ResponseEntity<Review> update(@RequestBody Review review) {
		return ResponseEntity.ok(reviewService.updateReview(review));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return reviewService.deleteBook(id) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
	}
}
