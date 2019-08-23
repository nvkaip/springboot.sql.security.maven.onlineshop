package com.controller;

import com.entity.Code;
import com.entity.Order;
import com.entity.User;
import com.service.MailService;
import com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/mail")
public class MailController {

    private MailService mailService;
    private OrderService orderService;

    @Autowired
    public MailController(MailService mailService, OrderService orderService) {
        this.mailService = mailService;
        this.orderService = orderService;
    }

    @PostMapping("/send/code")
    public String sendValidationCode(@AuthenticationPrincipal User user,
                                     @RequestParam("address") String address,
                                     Model model){
        Optional<Order> optionalOrder = orderService.getOrderByUserId(user.getId());
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            Code code = order.getValidationCode();
            mailService.sendConfirmCode(code);
            model.addAttribute("productList", order.getBasket().getProductsList());
        }
        model.addAttribute("address", address);
        return "order";
    }
}
