package com.example.newswebsite.controllers;

import com.example.newswebsite.dtos.UserDto;
import com.example.newswebsite.entities.Article;
import com.example.newswebsite.entities.User;
import com.example.newswebsite.exceptions.ConflictedOldValueException;
import com.example.newswebsite.exceptions.DuplicatedValueException;
import com.example.newswebsite.exceptions.NonexistentValueException;
import com.example.newswebsite.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    /***
     * @author: Unfame
     * @return: Return all account
     * @throws NonexistentValueException : Return Exception if something wrong happen
     */
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser() throws Exception{
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    /***
     * @author: Unfame
     * @return: Return all account
     * @throws NonexistentValueException : Return Exception if no User was founded
     */
    @GetMapping("/{userId}")
    public ResponseEntity<User> getOneUser(@Valid @PathVariable String userId) throws NonexistentValueException{
        return new ResponseEntity<>(userService.getOneUser(userId), HttpStatus.OK);
    }

    /***
     * @author: Unfame
     * @return: Account's information has just been logged in
     * @throws NonexistentValueException : Return Exception if no User was founded
     * @throws ConflictedOldValueException : Return Exception if the current value is matched with old value
     */
    @PutMapping("")
    public ResponseEntity<User> updateUser(@Valid @RequestBody UserDto userDto) throws NonexistentValueException, ConflictedOldValueException, DuplicatedValueException {
        return new ResponseEntity<>(userService.updateUser(userDto), HttpStatus.OK);
    }

    /***
     * @author: idtruoc
     * @return: User with list favorite Article has been added
     * @throws NonexistentValueException : Return Exception if the request user doesn't exist
     */
    @PutMapping("/favoriteArticle/add")
    public ResponseEntity<User> addFavorite(@Valid @RequestBody Map<String, String> data) throws NonexistentValueException{
        return new ResponseEntity<>(userService.addFavoriteArticle(data), HttpStatus.OK);
    }
    /***
     * @author: idtruoc
     * @return: User with list favorite Article has been added
     * @throws NonexistentValueException : Return Exception if the request user doesn't exist
     */
    @PutMapping("/favoriteArticle/remove")
    public ResponseEntity<User> removeFavorite(@Valid @RequestBody Map<String, String> data) throws NonexistentValueException{
        return new ResponseEntity<>(userService.removeFavoriteArticle(data), HttpStatus.OK);
    }

    /***
     * @author: Unfame
     * @return: Boolean is like or not
//     * @throws NonexistentValueException : Return Exception if the request user doesn't exist
     */
    @GetMapping("/isLiked/{userId}/{articleId}")
    public ResponseEntity<Boolean> isLiked(@Valid @PathVariable String userId, @Valid @PathVariable String articleId) throws Exception{
        return new ResponseEntity<>(userService.isLiked(userId, articleId), HttpStatus.OK);
    }
}
