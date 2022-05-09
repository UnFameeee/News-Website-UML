package com.example.newswebsite.repositories;

import com.example.newswebsite.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    @Query("{}")
    List<User> findAllUser();

    @Query("{'email': ?0}")
    Optional<User> findUserByEmail(String email);

    @Query("{phone:'?0'}")
    Optional<User> findUserByPhone(String phone);

    @Query("{account: {username: '?0'}}")
    Optional<User> findUserByUsername(String username);

//    @Query("{address : { $regex: ?0 } }")
//    List<User> findCustomByRegExAddress(String domain);
}

