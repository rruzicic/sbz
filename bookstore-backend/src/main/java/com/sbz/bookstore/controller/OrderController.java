package com.sbz.bookstore.controller;

import com.sbz.bookstore.dto.OrderDTO;
import com.sbz.bookstore.model.Book;
import com.sbz.bookstore.model.CustomUserDetails;
import com.sbz.bookstore.model.Item;
import com.sbz.bookstore.model.Order;
import com.sbz.bookstore.service.BookService;
import com.sbz.bookstore.service.OrderService;
import com.sbz.bookstore.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) { return ResponseEntity.ok(orderService.getById(id)); }

    @GetMapping("/price/{id}")
    public ResponseEntity<Double> getOrderPrice(@PathVariable Long id) { return ResponseEntity.ok(orderService.CalculateOrderPrice(id).getTotal_price()); }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/new")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody Order order) {
        Long userId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        order.setUser(userService.getById(userId));
        Order createdOrder = orderService.createOrder(order);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrder(createdOrder);
        orderDTO.setTotalPrice(createdOrder.getTotal_price());
        return ResponseEntity.ok(orderDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return orderService.deleteOrder(id) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping ("/buy/{orderId}")
    public ResponseEntity<List<Book>> getBooksAfterOrder(@PathVariable("orderId") long id){
        Order orderData = orderService.getById(id);
        if(orderData == null)
            return ResponseEntity.badRequest().build();

        for(Item item: orderData.getItems()){
            if (item.getQuantity() < item.getBook().getNumberOfCopies()){
                Book b = item.getBook();
                b.setNumberOfCopies(b.getNumberOfCopies() - item.getQuantity());
                bookService.updateBook(b);
            }

        }

        return ResponseEntity.ok(bookService.getAll());
    }

}
