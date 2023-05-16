package com.sbz.bookstore.controller;

import com.sbz.bookstore.model.Book;
import com.sbz.bookstore.service.BookService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookService bookService;

	@GetMapping("/all")
	public ResponseEntity<List<Book>> getAll() {
		return ResponseEntity.ok(bookService.getAll());
	}

	@GetMapping("/recommend/unauth")
	public ResponseEntity<List<Book>> recommendToUnauthorized() {
		return ResponseEntity.ok(bookService.getRecommendedUnauthorized());
	}

	@GetMapping("/recommend/regular/{userId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Book>> recommendToRegular(@PathVariable Long userId){
		return ResponseEntity.ok(bookService.getRecommendedRegular(userId));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getById(@PathVariable Long id) {
		return ResponseEntity.ok(bookService.getById(id));
	}

	@PostMapping(value = "/new", consumes = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		return bookService.createBook(book) != null ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
	}

	@PostMapping("/update")
	public ResponseEntity<Book> update(@RequestBody Book book) {
		return ResponseEntity.ok(bookService.updateBook(book));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return bookService.deleteBook(id) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
	}
}
