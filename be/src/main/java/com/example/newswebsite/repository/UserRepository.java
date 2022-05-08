package com.example.newswebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.newswebsite.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    User findPasswordByEmail(String username);

}

