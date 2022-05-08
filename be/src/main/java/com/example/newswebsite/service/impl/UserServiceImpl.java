package com.example.newswebsite.service.impl;

import com.example.newswebsite.model.User;
import com.example.newswebsite.repository.UserRepository;
import com.example.newswebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
    @Override
    public Optional<User> findUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findPasswordByEmail(email));
    }

}
