package com.service.impl;

import com.entity.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Optional;

public class UserDetailsServiceImp implements UserDetailsService {

    private UserService userService;

    @Autowired
    public UserDetailsServiceImp(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userService.getUserByEmail(email);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }
}
