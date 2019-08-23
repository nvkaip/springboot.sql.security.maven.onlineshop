package com.service.impl;

import com.entity.User;
import com.repository.UserRepository;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Transactional
    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Transactional
    @Override
    public void removeUser(Long userId) {
        userRepository.existsById(userId);
    }
}
