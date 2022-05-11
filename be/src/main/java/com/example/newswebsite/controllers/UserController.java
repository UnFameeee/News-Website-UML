package com.example.newswebsite.controllers;

import com.example.newswebsite.dtos.UserDto;
import com.example.newswebsite.entities.User;
import com.example.newswebsite.exceptions.ConflictedOldValueException;
import com.example.newswebsite.exceptions.DuplicatedValueException;
import com.example.newswebsite.exceptions.NonexistentUserException;
import com.example.newswebsite.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")

public class UserController {
    @Autowired
    private UserService userService;

    /***
     * @author: Unfame
     * @return: Return all account
     */
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    /***
     * @author: Unfame
     * @return: Account's information has just been logged in
     * @throws NonexistentUserException : Return Exception if no User was founded
     * @throws ConflictedOldValueException : Return Exception if the current value is matched with old value
     */
    @PutMapping("")
    public ResponseEntity<User> updateUser(@Valid @RequestBody UserDto userDto) throws NonexistentUserException, ConflictedOldValueException, DuplicatedValueException {
        return new ResponseEntity<>(userService.updateUser(userDto), HttpStatus.OK);
    }
}
