package com.example.newswebsite.services.auth;

import com.example.newswebsite.dtos.UserDto;
import com.example.newswebsite.entities.User;
import com.example.newswebsite.exceptions.*;

public interface AuthService {
    User register(UserDto userDto) throws DuplicatedUsernameException, DuplicatedEmailException;
    User login(UserDto userDto) throws UserNotExistException, PasswordIncorrectException;
}
