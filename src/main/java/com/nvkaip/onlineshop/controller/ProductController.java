package com.nvkaip.onlineshop.controller;

import com.nvkaip.onlineshop.entity.Product;
import com.nvkaip.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add")
    public String addProduct(Model model){
        model.addAttribute("action", "/product/add");
        return "add_product";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam("price") Double price){
        Product product = new Product(name, description, price);
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit")
    public String editProduct(Model model,
                              @RequestParam("productId") Long productId){
        Optional<Product> optionalProduct = productService.getProduct(productId);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            setProductAttributes(productId,
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    model);
            model.addAttribute("action", "/product/edit");
        } else {
            model.addAttribute("action", "/product/add");
        }
        return "add_product";
    }

    @PostMapping("/edit")
    public String editProduct(@RequestParam("productId") Long productId,
                              @RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("price") Double price){
        Optional<Product> optionalProduct = productService.getProduct(productId);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            productService.saveProduct(product);
        }
        return "redirect:/products";
    }

    @PostMapping("/delete")
    public String deleteProduct(@RequestParam("productId") Long productId){
        productService.removeProduct(productId);
        return "redirect:/products";
    }

    private void setProductAttributes(Long productId, String name, String description, Double price,
                                      Model model) {
        model.addAttribute("productId", productId);
        model.addAttribute("name", name);
        model.addAttribute("description", description);
        model.addAttribute("price", price);
    }
}
