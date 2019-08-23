package com.service;

import com.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAll();
    void saveProduct(Product product);
    Optional<Product> getProduct(Long productId);
    void removeProduct(Long productId);
}
