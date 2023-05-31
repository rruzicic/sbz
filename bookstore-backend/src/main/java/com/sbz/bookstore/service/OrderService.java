package com.sbz.bookstore.service;

import com.sbz.bookstore.config.KieConfig;
import com.sbz.bookstore.model.Item;
import com.sbz.bookstore.model.Order;
import com.sbz.bookstore.repository.OrderRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemService itemService;

    public Order getById(Long id) { return orderRepository.findById(id).get(); }

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

    public Order CalculateOrderPrice(Long id) {
        KieContainer kieContainer = new KieConfig().kieContainer();
        KieSession kieSession = kieContainer.newKieSession();

        kieSession.getAgenda().getAgendaGroup("calculate price").setFocus();
        kieSession.fireAllRules();

        return getById(id);
    }

    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
