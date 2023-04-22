package com.sbz.bookstore.service;

import com.sbz.bookstore.model.CustomUserDetails;
import com.sbz.bookstore.model.User;
import com.sbz.bookstore.repository.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		Optional<User> optionalUsers = userRepository.findByEmail(email);

		optionalUsers
			.orElseThrow(() -> new UsernameNotFoundException("Email not found"));
		return optionalUsers
			.map(CustomUserDetails::new).get();
	}
}
