package com.service.impl;

import com.entity.Order;
import com.repository.OrderRepository;
import com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Transactional
    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    @Override
    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Transactional
    @Override
    public Optional<Order> getOrderByUserId(Long userId) {
        return orderRepository.getOrderByBasketUserId(userId);
    }

    @Transactional
    @Override
    public void removeOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
