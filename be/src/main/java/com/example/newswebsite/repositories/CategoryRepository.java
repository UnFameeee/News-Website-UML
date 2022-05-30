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

    @Query("{'_id': '?0', 'isActive': ?1}")
    Optional<Category> findCategoryByIdAndIsActive(String id, Boolean isActive);

    @Query("{'categoryName': '?0', 'isActive': ?1}")
    Optional<Category> findCategoryByCategoryNameAndIsActive(String categoryName, Boolean isActive);
}
