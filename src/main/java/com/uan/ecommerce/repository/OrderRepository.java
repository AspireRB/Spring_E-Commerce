package com.uan.ecommerce.repository;

import com.uan.ecommerce.model.Order;
import com.uan.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUser (User user);
}
