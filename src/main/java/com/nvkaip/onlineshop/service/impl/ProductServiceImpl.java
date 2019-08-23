package com.service.impl;

import com.entity.Product;
import com.repository.ProductRepository;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Transactional
    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Transactional
    @Override
    public Optional<Product> getProduct(Long productId) {
        return productRepository.findById(productId);
    }

    @Transactional
    @Override
    public void removeProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
