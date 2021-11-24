package com.alkemy.java.service;

import com.alkemy.java.dto.*;
import com.alkemy.java.model.Category;

public interface CategoryService {
    Category findById(Long id);
    CategoryDetailDTO findDTOById(Long id);
    CategoryDTO addCategory(CategoryRequestDTO category);
    PageDTO<CategoryDTO> findAll(Integer page);
    CategoryDTO updateCategory(CategoryRequestDTO category, Long id);
    void deleteCategory(Long id);
}
