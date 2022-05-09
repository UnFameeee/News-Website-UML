package com.example.newswebsite.services.auth;

import com.example.newswebsite.dtos.UserDto;
import com.example.newswebsite.entities.User;
import com.example.newswebsite.exceptions.DuplicatedEmailException;
import com.example.newswebsite.exceptions.DuplicatedPhoneException;
import com.example.newswebsite.exceptions.DuplicatedUsernameException;

public interface AuthService {
    User register(UserDto userDto) throws DuplicatedUsernameException, DuplicatedEmailException, DuplicatedPhoneException;
    User login(UserDto userDto);
}
