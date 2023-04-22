package com.sbz.bookstore.controller;

import com.sbz.bookstore.model.Order;
import com.sbz.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) { return ResponseEntity.ok(orderService.getById(id)); }

    @GetMapping("/price/{id}")
    public ResponseEntity<Double> getOrderPrice(@PathVariable Long id) { return ResponseEntity.ok(orderService.CalculateOrderPrice(id)); }

    @PostMapping("/new")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

}
