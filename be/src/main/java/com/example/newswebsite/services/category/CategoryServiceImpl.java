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
import java.util.Map;
import java.util.Optional;
@Service
class CategoryServiceImpl implements  CategoryService{
    private final CategoryRepository categoryRepository;
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
        Optional<Category> category = categoryRepository.findCategoryByIdAndIsActive(Id, true);
        if(category.isEmpty()){
            throw new NonexistentValueException("Category doesn't exist !!!");
        }
        return category.get();
    }

    @Override
    public Category createCategory(CategoryDto categoryDto) throws DuplicatedValueException {
        Category category = ModelMapperSingleton.getInstance().modelMapper().map(categoryDto, Category.class);
        Optional<Category> cate = categoryRepository.findCategoryByCategoryNameAndIsActive(category.getCategoryName(), true);
        if(cate.isPresent()){
            throw new DuplicatedValueException("This category has been used before !!!");
        }
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category updateCategory(Map<String, String> data) throws DuplicatedValueException {
        Optional<Category> cate = categoryRepository.findCategoryByCategoryNameAndIsActive(data.get("categoryName") , true);
        if(cate.isEmpty()){
            throw new DuplicatedValueException("This category has been used before !!!");
        }
        cate.get().setCategoryName(data.get("newCategoryName"));
        categoryRepository.save(cate.get());
        return cate.get();
    }
}
