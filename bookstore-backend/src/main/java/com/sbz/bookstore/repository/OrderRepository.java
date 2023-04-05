package com.sbz.bookstore.repository;

import com.sbz.bookstore.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}