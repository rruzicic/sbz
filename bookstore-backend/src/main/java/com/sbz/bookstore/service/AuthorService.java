package com.sbz.bookstore.service;

import com.sbz.bookstore.model.Author;
import com.sbz.bookstore.model.Book;
import com.sbz.bookstore.model.Genre;
import com.sbz.bookstore.repository.AuthorRepository;
import com.sbz.bookstore.repository.BookRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
	@Autowired
	private AuthorRepository authorRepository;

	public List<Author> getAll() {
		return authorRepository.findAll();
	}

	public Author getById(Long id) {
		return authorRepository.findById(id).get();
	}

	public Author createBook(Author author) {
		return authorRepository.save(author);
	}

	public Author updateBook(Author author) {
		return authorRepository.save(author);
	}

	public boolean deleteBook(Long id) {
		if (authorRepository.existsById(id)) {
			authorRepository.deleteById(id);
			return true;
		}
		return false;
	}


}