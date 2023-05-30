package com.sbz.bookstore.service;

import com.sbz.bookstore.config.KieConfig;
import com.sbz.bookstore.model.Book;
import com.sbz.bookstore.model.Item;
import com.sbz.bookstore.model.Order;
import com.sbz.bookstore.model.User;
import com.sbz.bookstore.repository.AuthorRepository;
import com.sbz.bookstore.repository.BookRepository;
import com.sbz.bookstore.repository.OrderRepository;
import com.sbz.bookstore.repository.UserRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getById(Long id) { return orderRepository.findById(id).get(); }

    public List<Order> getOrdersByUserId(Long userId){
        User user = userRepository.findById(userId).get();
        List<Order> list = new ArrayList<>();
        List<Order> allOrders = getAll();
        for(Order order: allOrders){
            if(userId == order.getUser().getId()){
                list.add(order);
            }
        }
        return list;
    }

    public List<Book> extractBooksFromOrder(Order order){
        List<Book> result = new ArrayList<>();
        for(Item item:order.getItems()){
            result.add(item.getBook());
        }
        return result;
    }




    public Order createOrder(Order order) {
        // search for rules to apply to the items in the order that was just created
        KieContainer kieContainer = new KieConfig().kieContainer();
        KieSession kieSession = kieContainer.newKieSession();

        // add everything that's new to fact base
        kieSession.insert(order);
        for (Item item: order.getItems()) {
            kieSession.insert(item);
        }

        kieSession.getAgenda().getActivationGroup("item discounts");
        kieSession.fireAllRules();

        // do the same for the order
        kieSession.getAgenda().getActivationGroup("order discounts");
        kieSession.fireAllRules();

        return orderRepository.save(order);
    }

    public Double CalculateOrderPrice(Long id) {
        Order order = getById(id);

        // Returns the price of the order based on whether the items' price with their discount is lower than the items' price with the order discount
        double itemsPriceWithDiscount = itemService.CalculateOrderItemsPriceWithDiscount(order.getItems());
        double itemsPriceWithoutDiscount = itemService.CalculateOrderItemsPriceWithoutDiscount(order.getItems());

        if (itemsPriceWithDiscount < itemsPriceWithoutDiscount * (1.0 - order.getDiscount())) { return itemsPriceWithDiscount; }

        return itemsPriceWithoutDiscount * (1.0 - order.getDiscount());
    }

    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
