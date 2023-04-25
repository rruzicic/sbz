package com.sbz.bookstore.service;

import com.sbz.bookstore.model.Role;
import com.sbz.bookstore.model.User;
import com.sbz.bookstore.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public User login(String email, String password) {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (optionalUser.isEmpty()) { return null; }
		User user = optionalUser.get();
		if (!user.getPassword().equals(password)) { return null; }
		return user;
	}
	public User getById(Long id) {
		return userRepository.findById(id).get();
	}

	public User registerUser(User user) {
		user.setRole(Role.USER);
		return userRepository.save(user);
	}

	public User updateUser(User user) {
		return userRepository.save(user);
	}

	public boolean deleteBook(Long id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
