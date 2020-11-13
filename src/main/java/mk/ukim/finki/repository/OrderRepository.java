package mk.ukim.finki.repository;

import mk.ukim.finki.bootstrap.DataHolder;
import mk.ukim.finki.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {

    public Optional<Order> findByNameAndAddress(String name, String address){
        return DataHolder.orders.stream().filter(o -> o.getClientName().equals(name) && o.getClientAddress().equals(address)).findFirst();
    }

    public List<Order> findAll() {
        return DataHolder.orders;
    }
}
