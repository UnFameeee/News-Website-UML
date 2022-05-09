package com.example.newswebsite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicatedPhoneException extends Exception{
    public DuplicatedPhoneException(String message){
        super(message);
    }
}
