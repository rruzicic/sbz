package com.sbz.bookstore.controller;

import com.sbz.bookstore.model.CustomUserDetails;
import com.sbz.bookstore.model.Review;
import com.sbz.bookstore.service.ReviewService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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

	@PostMapping("/{bookId}/{rating}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> reviewBook(@PathVariable Long bookId, @PathVariable double rating) {
		Long userId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		return reviewService.reviewBook(userId, bookId, rating) != null ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();

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
