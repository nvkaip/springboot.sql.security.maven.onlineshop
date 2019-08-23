package com.controller;

import com.entity.Basket;
import com.entity.Product;
import com.entity.User;
import com.service.BasketService;
import com.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/buy")
public class BasketController {

    private final Logger logger = LoggerFactory.getLogger(BasketController.class);
    private BasketService basketService;
    private ProductService productService;

    @Autowired
    public BasketController(BasketService basketService, ProductService productService) {
        this.basketService = basketService;
        this.productService = productService;
    }

    @PostMapping("/add/product")
    public String addProductToBasket(@AuthenticationPrincipal User user,
                                     @RequestParam("productId") Long productId) {
        Basket basket;
        if (!basketService.getBasketByUserId(user.getId()).isPresent()) {
            basket = new Basket(user);
            basketService.saveBasket(basket);
            logger.info("Basket was created for " + user);
        }
        Optional<Basket> optionalBasket = basketService.getBasketByUserId(user.getId());
        if (optionalBasket.isPresent()) {
            basket = optionalBasket.get();
            Optional<Product> optionalProduct = productService.getProduct(productId);
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                basketService.addProduct(basket, product);
            }
        }
        return "redirect:/products";
    }

    @GetMapping("/basket")
    public String basketView(@AuthenticationPrincipal User user, Model model) {
        Optional<Basket> optionalBasket = basketService.getBasketByUserId(user.getId());
        if (optionalBasket.isPresent()){
            Basket basket = optionalBasket.get();
            model.addAttribute("productList", basket.getProductsList());
        }
        return "basket";
    }

    @PostMapping("/remove/product")
    public String deleteProductFromBasket(@AuthenticationPrincipal User user,
                                          @RequestParam("productId") Long productId){
        Optional<Basket> optionalBasket = basketService.getBasketByUserId(user.getId());
        Optional<Product> optionalProduct = productService.getProduct(productId);
        if (optionalBasket.isPresent() && optionalProduct.isPresent()){
            Basket basket = optionalBasket.get();
            Product product = optionalProduct.get();
            basketService.removeProduct(basket, product);
        } else {
            logger.warn("Can't fined basket with user ID " + user.getId());
        }
        return "redirect:/buy/basket";
    }
}
