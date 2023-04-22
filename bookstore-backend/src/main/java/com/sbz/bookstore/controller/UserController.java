package com.sbz.bookstore.controller;

import com.sbz.bookstore.model.User;
import com.sbz.bookstore.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserService userService;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAll() {
		return ResponseEntity.ok(userService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id) {
		return ResponseEntity.ok(userService.getById(id));
	}

	@PostMapping("/new")
	public ResponseEntity<User> createBook(@RequestBody User user) {
		return ResponseEntity.ok(userService.createUser(user));
	}

	@PostMapping("/update")
	public ResponseEntity<User> update(@RequestBody User user) {
		return ResponseEntity.ok(userService.updateUser(user));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return userService.deleteBook(id) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
	}
}
