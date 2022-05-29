package com.example.newswebsite.services.category;

import com.example.newswebsite.dtos.CategoryDto;
import com.example.newswebsite.entities.Article;
import com.example.newswebsite.entities.Category;
import com.example.newswebsite.exceptions.DuplicatedValueException;
import com.example.newswebsite.exceptions.NonexistentValueException;

import com.example.newswebsite.repositories.CategoryRepository;
import com.example.newswebsite.utils.ModelMapperSingleton;
import org.springframework.stereotype.Service;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
class CategoryServiceImpl implements  CategoryService{
    private final  CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategory() throws Exception {
        try{
            return categoryRepository.findAllCategory();
        }catch(Exception ex){
            throw new Exception("System error, detail: " + ex);
        }
    }

    @Override
    public Category getOneCategory(String Id) throws NonexistentValueException {
        Optional<Category> category = categoryRepository.findCategoryById(Id);
        if(category.isEmpty()){
            throw new NonexistentValueException("Category doesn't exist !!!");
        }
        return category.get();
    }

    @Override
    public Category creatingCategory(CategoryDto categoryDto) throws DuplicatedValueException {
        Category category = ModelMapperSingleton.getInstance().modelMapper().map(categoryDto, Category.class);
        if(categoryRepository.findCategoryByCategoryName(category.getCategoryName()).isPresent()){
            throw new DuplicatedValueException("This category has been used before !!!");
        }

        categoryRepository.save(category);
        return category;
    }
}
