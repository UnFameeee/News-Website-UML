package com.example.newswebsite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class PasswordIncorrectException extends Exception{
    public PasswordIncorrectException(String message){
        super(message);
    }
}
