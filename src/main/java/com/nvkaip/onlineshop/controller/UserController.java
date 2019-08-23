package com.nvkaip.onlineshop.controller;

import com.nvkaip.onlineshop.entity.Role;
import com.nvkaip.onlineshop.entity.User;
import com.nvkaip.onlineshop.service.RoleService;
import com.nvkaip.onlineshop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String allUsers(Model model) {
        model.addAttribute("userList", userService.getAll());
        return "users";
    }

    @GetMapping("/add/user")
    public String addUser(Model model) {
        model.addAttribute("action", "/admin/add/user");
        return "register";
    }

    @PostMapping("/add/user")
    public String addUser(Model model,
                          @RequestParam("email") String email,
                          @RequestParam("accessRole") String accessRole,
                          @RequestParam("password") String password,
                          @RequestParam("rpassword") String reEnteredPassword) {
        if (password.equals(reEnteredPassword) && !password.isEmpty()) {
            User user = generateUser(email, password, accessRole);
            userService.saveUser(user);
            return "redirect:/admin/users";
        } else {
            model.addAttribute("error", "Passwords are not equal");
            model.addAttribute("email", email);
            model.addAttribute("accessRole", accessRole);
            return "register";
        }
    }

    @GetMapping("/edit/user")
    public String editUser(Model model,
                           @RequestParam("userId") Long userId) {
        Optional<User> optionalUser = userService.getUserById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            setUserAttributes(userId, user.getEmail(), model);
            return "register";
        } else {
            logger.info("Couldn't get user by Id = " + userId);
            return "redirect:/admin/users";
        }
    }

    @PostMapping("/edit/user")
    public String editUser(Model model,
                           @RequestParam("userId") Long userId,
                           @RequestParam("email") String email,
                           @RequestParam("accessRole") String accessRole,
                           @RequestParam("password") String password,
                           @RequestParam("rpassword") String reEnteredPassword) {
        if (password.equals(reEnteredPassword) && !password.isEmpty()) {
            User user = generateUser(email, password, accessRole);
            user.setId(userId);
            userService.saveUser(user);
            return "redirect:/admin/users";
        } else {
            model.addAttribute("error", "Your passwords are incorrect");
            setUserAttributes(userId, email, model);
            return "register";
        }
    }

    @PostMapping("/delete/user")
    public String deleteUser(@RequestParam("userId") Long userId) {
        userService.removeUser(userId);
        return "redirect:/admin/users";
    }

    private User generateUser (String email, String password, String accessRole){
        Optional<User> optionalUser = userService.getUserByEmail(email);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        password = passwordEncoder.encode(password);
        Set<Role> roleList;
        if (optionalUser.isPresent()){
            roleList = optionalUser.get().getRoleList();
        } else {
            roleList = new HashSet<>();
        }
        Optional<Role> optionalAccessRole = roleService.getRoleByName(accessRole);
        optionalAccessRole.ifPresent(roleList::add);
        return new User(email, password, roleList);
    }

    private void setUserAttributes(Long userId, String email, Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("email", email);
        model.addAttribute("action", "/admin/edit/user");
    }
}
