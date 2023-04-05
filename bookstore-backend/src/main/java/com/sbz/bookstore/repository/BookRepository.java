package com.sbz.bookstore.repository;

import com.sbz.bookstore.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}