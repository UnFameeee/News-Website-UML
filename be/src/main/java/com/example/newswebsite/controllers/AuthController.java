package com.example.newswebsite.controllers;

import com.example.newswebsite.dtos.UserDto;
import com.example.newswebsite.entities.User;
import com.example.newswebsite.exceptions.DuplicatedEmailException;
import com.example.newswebsite.exceptions.DuplicatedPhoneException;
import com.example.newswebsite.exceptions.DuplicatedUsernameException;
import com.example.newswebsite.services.auth.AuthService;
import com.example.newswebsite.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")


public class AuthController {
    @Autowired
    private AuthService authService;

    /***
     * @author: quocthang
     * @return: Thông tin nhân viền vừa được thêm dưới role là NHAN_VIEN
     * @throws DuplicatedEmailException : Return Exception if Email was duplicated
     * @throws DuplicatedUsernameException : Return Exception if Username was duplicated
     * @throws DuplicatedPhoneException : Return Exception if Phone was duplicated
     */
    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody UserDto userDto) throws DuplicatedUsernameException, DuplicatedEmailException, DuplicatedPhoneException{
        return new ResponseEntity<>(authService.register(userDto), HttpStatus.OK);
    }



    @PostMapping("/login")
    public ResponseEntity<User> login(@Valid @RequestBody UserDto userDto){
        return new ResponseEntity<>(authService.login(userDto), HttpStatus.OK);
    }
}
