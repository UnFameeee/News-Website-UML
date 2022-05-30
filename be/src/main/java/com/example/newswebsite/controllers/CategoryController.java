package com.example.newswebsite.controllers;

import com.example.newswebsite.dtos.CategoryDto;
import com.example.newswebsite.entities.Category;
import com.example.newswebsite.exceptions.DuplicatedValueException;
import com.example.newswebsite.exceptions.NonexistentValueException;
import com.example.newswebsite.services.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /***
     * @author: idtruoc, Unfame
     * @return: All category in the database (but isActive = true)
     * @throws Exception : Return Exception if something wrong
     */
    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategory() throws Exception{
        return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
    }

    /***
     * @author: idtruoc, Unfame
     * @return: Category required
     * @throws NonexistentValueException : Return Exception if the request Category doesn't exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<Category> getOneCategory(@Valid @PathVariable String id) throws NonexistentValueException {
        return new ResponseEntity<>(categoryService.getOneCategory(id), HttpStatus.OK);
    }

    /***
     * @author: idtruoc, Unfame
     * @return: The category has just been created
     * @throws DuplicatedValueException : Return Exception if the title of the category is duplicated
     */
    @PostMapping("")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryDto categoryDto) throws DuplicatedValueException{
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.OK);
    }

    /***
     * @author: Unfame
     * @return: Upadte category has just been created
     * @throws DuplicatedValueException : Return Exception if the title of the category is duplicated
     */
    @PutMapping("")
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody Map<String, String> data) throws DuplicatedValueException{
        return new ResponseEntity<>(categoryService.updateCategory(data), HttpStatus.OK);
    }
}
