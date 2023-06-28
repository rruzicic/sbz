package com.sbz.bookstore.service;

import com.sbz.bookstore.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private Double CalculateItemPriceWithoutDiscount(Item item) {
        return item.getBook().getPrice() * item.getQuantity();
    }

    public Double CalculateOrderItemsPriceWithoutDiscount(List<Item> items) {
        double sum = 0;
        for (Item item: items) {
            sum += CalculateItemPriceWithoutDiscount(item);
        }

        return sum;
    }

    private Double CalculateItemPriceWithDiscount(Item item) {
        return item.getBook().getPrice() * item.getQuantity() * (1.0 - item.getDiscount());
    }

    public Double CalculateOrderItemsPriceWithDiscount(List<Item> items) {
        double sum = 0;
        for (Item item: items) {
            sum += CalculateItemPriceWithDiscount(item);
        }

        return sum;
    }
}
