package com.nvkaip.onlineshop.service.impl;

import com.nvkaip.onlineshop.entity.Basket;
import com.nvkaip.onlineshop.entity.Product;
import com.nvkaip.onlineshop.repository.BasketRepository;
import com.nvkaip.onlineshop.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BasketServiceImpl implements BasketService {

    private BasketRepository basketRepository;

    @Autowired
    public BasketServiceImpl(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public List<Basket> getAll() {
        return basketRepository.findAll();
    }

    @Override
    public void saveBasket(Basket basket) {
        basketRepository.save(basket);
    }

    @Override
    public Optional<Basket> getBasketById(Long basketId) {
        return basketRepository.findById(basketId);
    }

    @Override
    public Optional<Basket> getBasketByUserId(Long userId) {
        return basketRepository.getBasketByUserId(userId);
    }

    @Override
    public void clearBasket(Long basketId) {
        if (getBasketById(basketId).isPresent()) {
            Basket basket = getBasketById(basketId).get();
            basket.getProductsList().clear();
            basketRepository.save(basket);
        }
    }

    @Override
    public void addProduct(Basket basket, Product product) {
        basket.getProductsList().add(product);
        basketRepository.save(basket);
    }

    @Override
    public void removeProduct(Basket basket, Product product) {
        basket.getProductsList().remove(product);
        basketRepository.save(basket);
    }
}
