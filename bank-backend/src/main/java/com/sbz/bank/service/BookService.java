package com.sbz.bank.service;

import com.sbz.bank.config.KieConfig;
import com.sbz.bank.model.Book;
import com.sbz.bank.repository.BookRepository;

import java.time.LocalDate;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;

	public List<Book> getAll() {
		return bookRepository.findAll();
	}

	public Book getById(Long id) {
		return bookRepository.findById(id).get();
	}

	public Book createBook(Book book) {
		if (book == null) { return null; }
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
