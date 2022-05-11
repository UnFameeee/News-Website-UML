package com.example.newswebsite.configs;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncryptPassSingleton {
    private static EncryptPassSingleton instance;
    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private EncryptPassSingleton(){
    }

    public static EncryptPassSingleton getInstance(){
        if(instance == null){
            synchronized (EncryptPassSingleton.class){
                if(instance == null){
                    instance = new EncryptPassSingleton();
                }
            }
        }
        return instance;
    }

    public String encrypt(String pass){
        return passwordEncoder.encode(pass);
    }

    public boolean compare(String rawPass, String encodePass){
        return passwordEncoder.matches(rawPass, encodePass);
    }
}
