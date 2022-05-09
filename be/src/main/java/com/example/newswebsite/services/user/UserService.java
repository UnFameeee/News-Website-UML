package com.example.newswebsite.services.user;
import com.example.newswebsite.dtos.UserDto;
import com.example.newswebsite.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
}
