package com.nvkaip.onlineshop.controller;

import com.nvkaip.onlineshop.entity.Basket;
import com.nvkaip.onlineshop.entity.Code;
import com.nvkaip.onlineshop.entity.Order;
import com.nvkaip.onlineshop.entity.User;
import com.nvkaip.onlineshop.service.BasketService;
import com.nvkaip.onlineshop.service.CodeService;
import com.nvkaip.onlineshop.service.OrderService;
import com.nvkaip.onlineshop.util.CodeGenerator;
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
@RequestMapping("/order")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private OrderService orderService;
    private BasketService basketService;
    private CodeService codeService;

    @Autowired
    public OrderController(OrderService orderService, BasketService basketService, CodeService codeService) {
        this.orderService = orderService;
        this.basketService = basketService;
        this.codeService = codeService;
    }

    @GetMapping("/make")
    public String makeOrder(@AuthenticationPrincipal User user,
                            Model model) {
        Optional<Order> optionalOrder = orderService.getOrderByUserId(user.getId());
        Order order;
        if (optionalOrder.isPresent()) {
            order = optionalOrder.get();
        } else {
            Code code = new Code(CodeGenerator.getValidationCode(), user);
            codeService.add(code);
            code = codeService.getCodeByUserId(user.getId()).get();
            order = new Order(code);
            Optional<Basket> optionalBasket = basketService.getBasketByUserId(user.getId());
            optionalBasket.ifPresent(order::setBasket);
            orderService.saveOrder(order);
        }
        model.addAttribute("productList", order.getBasket().getProductsList());
        return "order";
    }

    @PostMapping("/submit")
    public String submitOrder(@AuthenticationPrincipal User user,
                              @RequestParam("address") String address,
                              @RequestParam("code") String code,
                              Model model) {
        Optional<Order> optionalOrder = orderService.getOrderByUserId(user.getId());
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (order.getValidationCode().getValue().equals(code)) {
                order.setValidCode(true);
                order.getBasket().setOrdered(true);
                orderService.saveOrder(order);
                logger.info("Order " + order + " was validated");
                model.addAttribute("code", "Thank you for your order");
                model.addAttribute("address", address);
                model.addAttribute("productList", order.getBasket().getProductsList());
            } else {
                model.addAttribute("code", "Please, enter valid code");
                model.addAttribute("address", address);
                model.addAttribute("productList", order.getBasket().getProductsList());
                logger.info("Basket " + order + " was not validated. Incorrect code - " + code);
            }
        }
        return "order";
    }
}
