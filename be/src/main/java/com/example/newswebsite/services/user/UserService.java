package com.example.newswebsite.services.user;
import com.example.newswebsite.dtos.UserDto;
import com.example.newswebsite.entities.User;
import com.example.newswebsite.exceptions.ConflictedOldValueException;
import com.example.newswebsite.exceptions.DuplicatedValueException;
import com.example.newswebsite.exceptions.NonexistentUserException;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    User updateUser(UserDto userDto) throws NonexistentUserException, ConflictedOldValueException, DuplicatedValueException;
}
