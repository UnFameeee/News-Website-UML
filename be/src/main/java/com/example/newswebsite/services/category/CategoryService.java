package com.example.newswebsite.services.category;

import com.example.newswebsite.dtos.ArticleDto;
import com.example.newswebsite.dtos.CategoryDto;
import com.example.newswebsite.entities.Article;
import com.example.newswebsite.entities.Category;
import com.example.newswebsite.exceptions.DuplicatedValueException;
import com.example.newswebsite.exceptions.NonexistentValueException;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    List<Category> getAllCategory() throws Exception;
    Category getOneCategory(String Id) throws NonexistentValueException;
    Category createCategory(CategoryDto categoryDto) throws DuplicatedValueException;
    Category updateCategory(Map<String, String> data) throws DuplicatedValueException;
}
