package com.example.newswebsite.controller;

import com.example.newswebsite.model.User;
import com.example.newswebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")

public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/users/test")
    public ResponseEntity< String> findPasswordByEmssail( ) {
        try{
            return new ResponseEntity<>("users", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/users/getall")
    public ResponseEntity<List<User>> getAllUser( ) {
        try {
            List<User> userList = userService.getAllUser();

            if (userList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/checkEmail/{email}")
    public ResponseEntity<Optional<User>> getUserByEmail(@PathVariable("email") String email ) {
        try {

            Optional<User> users = userService.findUserByEmail(email);

            if (!users.isEmpty()) {
                return new ResponseEntity<>(users,HttpStatus.OK);
            }
            return new ResponseEntity<>( users,HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/users/add")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User _user = userService.saveUser(new User(user.getUsername(),user.getPassword(),user.getEmail(),user.getPhone(),user.getFullname(),user.getImage(),user.getRole() ));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/edit/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {

        Optional<User> userData = userService.findUserById( (long)id);

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setUsername(user.getUsername());
            _user.setPhone(user.getPhone());
            _user.setFullname(user.getFullname());
            return new ResponseEntity<>(userService.saveUser(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
        try {

            try{userService.deleteUser((long)id);}
            catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
