package com.uan.ecommerce.service;

import com.uan.ecommerce.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order save(Order order);
    String generateOrderNumber();

}
