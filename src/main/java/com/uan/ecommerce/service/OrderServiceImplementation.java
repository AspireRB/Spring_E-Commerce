package com.uan.ecommerce.service;

import com.uan.ecommerce.model.Order;
import com.uan.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImplementation implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public String generateOrderNumber() {
        int number = 0;
        String numberConcatenated = "";

        List<Order> orders = findAll();

        List<Integer> numbers = new ArrayList<Integer>();

        orders.stream().forEach(o -> numbers.add(Integer.parseInt(o.getNumber())));

        if (orders.isEmpty()) {
            number = 1;
        } else {
            number = numbers.stream().max(Integer::compare).get();
            number++;
        }

        if (number < 10) {
            numberConcatenated = "000000000"+String.valueOf(number);
        } else if (number < 100) {
            numberConcatenated = "00000000"+String.valueOf(number);
        } else if (number < 1000 ) {
            numberConcatenated = "0000000"+String.valueOf(number);
        } else if (number < 10000 ) {
            numberConcatenated = "000000"+String.valueOf(number);
        }

        return numberConcatenated;
    }
}
