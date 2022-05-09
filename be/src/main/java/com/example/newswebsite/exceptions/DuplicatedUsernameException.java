package com.example.newswebsite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicatedUsernameException extends Exception{
    public DuplicatedUsernameException(String message){
        super(message);
    }
}
