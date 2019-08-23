package com.repository;

import com.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    Optional<Basket> getBasketByUserId(Long userId);
}
