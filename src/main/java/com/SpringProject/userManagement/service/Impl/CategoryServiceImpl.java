package com.SpringProject.userManagement.service.Impl;
import com.SpringProject.userManagement.entity.Category;
import com.SpringProject.userManagement.exception.ResourceNotFoundException;
import com.SpringProject.userManagement.repository.CategoryRepository;
import com.SpringProject.userManagement.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

   // @Service
 //   public class CategoryServiceImpl implements CategoryService {

  // package com.SpringProject.userManagement.service.Impl;

//import com.SpringProject.userManagement.entity.Category;
//import com.SpringProject.userManagement.repository.CategoryRepository;
//import com.SpringProject.userManagement.service.CategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

//import java.util.List;

        @Service
        public class CategoryServiceImpl implements CategoryService {

            @Autowired
           private CategoryRepository categoryRepository;

            @Override
            public Category createCategory(Category category) {
                return categoryRepository.save(category);
            }
            @Override
            public Category getCategoryById(Long id) {
                return categoryRepository.findById(id)
                        .orElseThrow(() -> new
                                ResourceNotFoundException("Category not found "));
            }

            @Override
            public Category updateCategory(Long id, Category category) {

                Category existingCategory = categoryRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Category not found"));

                existingCategory.setCategoryName(category.getCategoryName());

                return categoryRepository.save(existingCategory);
            }

            @Override
            public void deleteCategory(Long id) {

                Category category = categoryRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Category not found"));

                categoryRepository.delete(category);
            }

            @Override
            public List<Category> getAllCategories() {
                return categoryRepository.findAll();
            }
        }

