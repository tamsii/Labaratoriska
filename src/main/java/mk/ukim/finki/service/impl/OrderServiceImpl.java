package mk.ukim.finki.service.impl;

import mk.ukim.finki.model.exception.NoSuchOrderException;
import mk.ukim.finki.model.Order;
import mk.ukim.finki.repository.OrderRepository;
import mk.ukim.finki.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(String clientName, String address) {
        if(orderRepository.findByNameAndAddress(clientName, address).isEmpty()){
            throw new NoSuchOrderException();
        }
        return orderRepository.findByNameAndAddress(clientName,address).get();
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
