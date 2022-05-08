package com.example.newswebsite.service;
import com.example.newswebsite.model.User;

import java.util.List;
import java.util.Optional;
public interface UserService {
    List<User> getAllUser();

    User saveUser(User user);

    void deleteUser(Long id);

    Optional<User> findUserById(Long id);
    Optional<User> findUserByEmail(String Email);
}
