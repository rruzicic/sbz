package com.sbz.bookstore.service;

import com.sbz.bookstore.model.Author;
import com.sbz.bookstore.model.Book;
import com.sbz.bookstore.repository.AuthorRepository;
import com.sbz.bookstore.repository.BookRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;

	public List<Book> getAll() {
		return bookRepository.findAll();
	}

	public Book getById(Long id) {
		return bookRepository.findById(id).get();
	}

	public Book createBook(Book book) {
		if (book == null) { return null; }
		if (book.getAuthor() == null) { return null; }
		if (book.getAuthor().getId() == null) { return null; }
		if (!authorRepository.existsById(book.getAuthor().getId())) { return null; }
		Author author = authorRepository.findById(book.getAuthor().getId()).get();
		book.setAuthor(author);
		book.setAddedToBookstoreDate(LocalDate.now());
		return bookRepository.save(book);
	}

	public Book updateBook(Book book) {
		return bookRepository.save(book);
	}

	public boolean deleteBook(Long id) {
		if (bookRepository.existsById(id)) {
			bookRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
