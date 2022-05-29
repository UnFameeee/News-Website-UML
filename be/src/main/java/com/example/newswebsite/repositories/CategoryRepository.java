package com.example.newswebsite.repositories;


import com.example.newswebsite.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository  extends MongoRepository<Category, String> {

    @Query("{}")
    List<Category> findAllCategory();

    @Query("{_id: '?0'}")
    Optional<Category> findCategoryById(String id);

    @Query("{categoryName: '?0'}")
    Optional<Category> findCategoryByCategoryName(String categoryName);


}
