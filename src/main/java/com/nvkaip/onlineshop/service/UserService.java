package com.service;

import com.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAll();
    void saveUser(User user);
    Optional<User> getUserById(Long userId);
    Optional<User> getUserByEmail(String email);
    void removeUser(Long userId);
}
