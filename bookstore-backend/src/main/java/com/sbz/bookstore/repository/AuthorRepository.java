package com.sbz.bookstore.repository;

import com.sbz.bookstore.model.Author;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}