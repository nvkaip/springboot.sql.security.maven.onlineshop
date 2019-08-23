package com.service;

import com.entity.Order;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAll();
    void saveOrder(Order order);
    Optional<Order> getOrderById(Long orderId);
    Optional<Order> getOrderByUserId(Long userId);
    void removeOrder(Long orderId);
}
