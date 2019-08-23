package com.nvkaip.onlineshop.repository;

import com.nvkaip.onlineshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> getOrderByBasketUserId(Long userId);
}
