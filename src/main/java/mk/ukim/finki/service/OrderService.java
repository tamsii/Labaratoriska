package mk.ukim.finki.service;


import mk.ukim.finki.model.Order;

import java.util.List;

public interface OrderService {

    Order placeOrder(String clientName, String address);

    List<Order> findAll();
}
