package com.SpringProject.userManagement.repository;
import com.SpringProject.userManagement.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category, Long> {
}
