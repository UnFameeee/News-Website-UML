package com.example.newswebsite.services.user;
import com.example.newswebsite.dtos.UserDto;
import com.example.newswebsite.entities.User;
import com.example.newswebsite.exceptions.ConflictedOldValueException;
import com.example.newswebsite.exceptions.DuplicatedValueException;
import com.example.newswebsite.exceptions.NonexistentValueException;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> getAllUser() throws Exception;
    User getOneUser(String id) throws NonexistentValueException;


    User updateUser(UserDto userDto) throws NonexistentValueException, ConflictedOldValueException, DuplicatedValueException;

    User addFavoriteArticle(Map<String, String> data) throws NonexistentValueException;

    User removeFavoriteArticle(Map<String, String> data) throws NonexistentValueException;

    Boolean isLiked(String userId, String articleId) throws Exception;
}
