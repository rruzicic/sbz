package com.sbz.bookstore.dto;

import com.sbz.bookstore.model.Order;
import lombok.Data;

@Data
public class OrderDTO {

    private Order order;

    private double totalPrice;

}
