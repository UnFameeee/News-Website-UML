package com.example.newswebsite.controllers;

import com.example.newswebsite.dtos.UserDto;
import com.example.newswebsite.entities.User;
import com.example.newswebsite.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser(){
        System.out.println(userService.getAllUser());
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

//    @GetMapping("/users/test")
//    public ResponseEntity< String> findPasswordByEmssail( ) {
//        try{
//            return new ResponseEntity<>("users", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @GetMapping("/users/getall")
//    public ResponseEntity<List<Users>> getAllUser( ) {
//        try {
//            List<Users> usersList = userService.getAllUser();
//
//            if (usersList.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(usersList, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/users/checkEmail/{email}")
//    public ResponseEntity<Optional<Users>> getUserByEmail(@PathVariable("email") String email ) {
//        try {
//
//            Optional<Users> users = userService.findUserByEmail(email);
//
//            if (!users.isEmpty()) {
//                return new ResponseEntity<>(users,HttpStatus.OK);
//            }
//            return new ResponseEntity<>( users,HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


//    @PostMapping("/users/add")
//    public ResponseEntity<Users> createUser(@RequestBody Users users) {
//        try {
//            Users _users = userService.saveUser(new Users(users.getUsername(), users.getPassword(), users.getEmail(), users.getPhone(), users.getFullname(), users.getImage(), users.getRole() ));
//            return new ResponseEntity<>(_users, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @PutMapping("/users/edit/{id}")
//    public ResponseEntity<Users> updateUser(@PathVariable("id") int id, @RequestBody Users users) {
//
//        Optional<Users> userData = userService.findUserById( (long)id);
//
//        if (userData.isPresent()) {
//            Users _users = userData.get();
//            _users.setUsername(users.getUsername());
//            _users.setPhone(users.getPhone());
//            _users.setFullname(users.getFullname());
//            return new ResponseEntity<>(userService.saveUser(_users), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }




//    @DeleteMapping("/users/{id}")
//    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
//        try {
//
//            try{userService.deleteUser((long)id);}
//            catch (Exception e) {
//                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
