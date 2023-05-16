package com.sbz.bank.controller;

import com.sbz.bank.dto.LoggedUserBasicInfoDTO;
import com.sbz.bank.dto.UserCredentialsDTO;
import com.sbz.bank.model.User;
import com.sbz.bank.service.UserService;

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

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserCredentialsDTO credentials) {
		User user = userService.login(credentials.getEmail(), credentials.getPassword());
		if(user == null) { return ResponseEntity.badRequest().build(); }
		LoggedUserBasicInfoDTO loggedUser = new LoggedUserBasicInfoDTO();
		loggedUser.setRole(user.getRole());
		loggedUser.setName(user.getFirstName() + " " + user.getLastName());
		return ResponseEntity.ok(loggedUser);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAll() {
		return ResponseEntity.ok(userService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id) {
		return ResponseEntity.ok(userService.getById(id));
	}

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) {
		return ResponseEntity.ok(userService.registerUser(user));
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
