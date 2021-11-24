package com.alkemy.java.mapper;

import com.alkemy.java.dto.CategoryDTO;
import com.alkemy.java.dto.CategoryDetailDTO;
import com.alkemy.java.dto.CategoryRequestDTO;
import com.alkemy.java.dto.CategoryResponseDTO;
import com.alkemy.java.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDetailDTO toDetailDTO(Category category);
    CategoryResponseDTO categoryToCategoryResponseDto(Category category);
    Category toCategory(CategoryRequestDTO categoryRequestDTO);
    CategoryDTO toDTO(Category category);
}
