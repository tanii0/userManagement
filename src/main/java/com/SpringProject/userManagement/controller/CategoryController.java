package com.SpringProject.userManagement.controller;

import com.SpringProject.userManagement.entity.Category;
import com.SpringProject.userManagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

    @RestController
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/categories")
    public class CategoryController {
        @Autowired
        CategoryService categoryService;


        @PostMapping("/add")


        public ResponseEntity<Category> createCategory(@RequestBody Category category) {
            Category createdCategory = categoryService.createCategory(category);
            return new ResponseEntity<>(createdCategory,HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public Category updateCategory(@PathVariable Long id,
                                       @RequestBody Category category) {
            return categoryService.updateCategory(id, category);
        }
        @GetMapping("/{id}")
        public Category getCategoryById(@PathVariable Long id) {
            return categoryService.getCategoryById(id);
        }

        @DeleteMapping("/{id}")
        public String deleteCategory(@PathVariable Long id) {

            categoryService.deleteCategory(id);

            return "Category deleted successfully";
        }

        @GetMapping("/all")
        public List<Category> getAllCategories() {
            return categoryService.getAllCategories();
        }
    }

