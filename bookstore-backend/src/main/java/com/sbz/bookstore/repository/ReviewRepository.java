package com.sbz.bookstore.repository;

import com.sbz.bookstore.model.Review;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}