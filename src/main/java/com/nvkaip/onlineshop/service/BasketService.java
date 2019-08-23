package com.service;

import com.entity.Basket;
import com.entity.Product;
import java.util.List;
import java.util.Optional;

public interface BasketService {

    List<Basket> getAll();
    void saveBasket(Basket basket);
    Optional<Basket> getBasketById(Long basketId);
    Optional<Basket> getBasketByUserId(Long userId);
    void clearBasket(Long basketId);
    void addProduct(Basket basket, Product product);
    void removeProduct(Basket basket, Product product);
}
