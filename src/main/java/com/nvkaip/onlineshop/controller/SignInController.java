package com.nvkaip.onlineshop.controller;

import com.nvkaip.onlineshop.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInController {

    private final Logger logger = LoggerFactory.getLogger(SignInController.class);

    @GetMapping("/")
    public String welcomePage() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signin")
    public String loginUser(@AuthenticationPrincipal User user) {
        logger.info(user + " entered the site");
        if (user.getRoleList().stream()
                .anyMatch(role -> role.getRoleName().equals("ROLE_ADMIN"))) {
            return "redirect:/admin/users";
        } else {
            return "redirect:/products";
        }
    }
}
