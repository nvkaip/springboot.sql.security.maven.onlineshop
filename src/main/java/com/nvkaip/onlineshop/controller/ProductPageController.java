package com.nvkaip.onlineshop.controller;

import com.nvkaip.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductPageController {

    private ProductService productService;

    @Autowired
    public ProductPageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String allProducts(Model model) {
        model.addAttribute("productList", productService.getAll());
        return "products";
    }
}
