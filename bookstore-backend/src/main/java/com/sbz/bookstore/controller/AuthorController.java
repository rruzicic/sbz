package com.sbz.bookstore.controller;

import com.sbz.bookstore.model.Author;
import com.sbz.bookstore.service.AuthorService;
import com.sbz.bookstore.service.BookService;

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
@RequestMapping("/author")
public class AuthorController {
	@Autowired
	private AuthorService authorService;

	@GetMapping("/all")
	public ResponseEntity<List<Author>> getAll() {
		return ResponseEntity.ok(authorService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Author> getById(@PathVariable Long id) {
		return ResponseEntity.ok(authorService.getById(id));
	}

	@PostMapping(value = "/new", consumes = "application/json")
	public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
		return ResponseEntity.ok(authorService.createBook(author));
	}

	@PostMapping("/update")
	public ResponseEntity<Author> update(@RequestBody Author author) {
		return ResponseEntity.ok(authorService.updateBook(author));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return authorService.deleteBook(id) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
	}
}
