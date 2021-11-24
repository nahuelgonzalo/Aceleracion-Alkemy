package com.alkemy.java.category;

import com.alkemy.java.dto.CategoryDTO;
import com.alkemy.java.dto.CategoryDetailDTO;
import com.alkemy.java.dto.CategoryRequestDTO;
import com.alkemy.java.dto.PageDTO;

import java.util.Arrays;
import java.util.Date;

public class CategoryTestUtil {
    public static final long FIRST_ID = 1L;

    public static final long SECOND_ID = 2L;

    public static CategoryDTO getFirstDTO() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(FIRST_ID);
        categoryDTO.setName("name1");
        categoryDTO.setImage("name1.png");
        categoryDTO.setDescription("descripcion1");
        return categoryDTO;
    }

    public static CategoryDTO getSecondDTO() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(SECOND_ID);
        categoryDTO.setName("name2");
        categoryDTO.setImage("name2.png");
        categoryDTO.setDescription("descripcion2");
        return categoryDTO;
    }

    public static CategoryRequestDTO getFirstRequestDTO() {
        CategoryRequestDTO dto = new CategoryRequestDTO();
        dto.setName("name1");
        dto.setImage("name1.png");
        dto.setDescription("descripcion1");
        return dto;
    }

    public static CategoryRequestDTO getSecondRequestDTO() {
        CategoryRequestDTO dto = new CategoryRequestDTO();
        dto.setName("name2");
        dto.setImage("name2.png");
        dto.setDescription("descripcion2");
        return dto;
    }

    public static CategoryRequestDTO getUpdateRequestDTO() {
        CategoryRequestDTO dto = new CategoryRequestDTO();
        dto.setName("UPDATE");
        dto.setImage("UPDATE.png");
        dto.setDescription("UPDATE_DESCRIPTION");
        return dto;
    }

    public static PageDTO<CategoryDTO> getPageDTO() {
        PageDTO<CategoryDTO> dto = new PageDTO<>();
        dto.setContent(Arrays.asList(getFirstDTO(), getSecondDTO()));
        dto.setTotalPages(1);
        return dto;
    }

    public static CategoryDTO getUpdateDTO(Long id) {
        CategoryRequestDTO updateRequestDTO = getUpdateRequestDTO();
        CategoryDTO dto = new CategoryDTO();
        dto.setDescription(updateRequestDTO.getDescription());
        dto.setName(updateRequestDTO.getName());
        dto.setImage(updateRequestDTO.getImage());
        dto.setId(id);
        return dto;
    }

    public static CategoryDetailDTO getDetailDTO(Long id) {
        CategoryRequestDTO updateRequestDTO = getUpdateRequestDTO();
        CategoryDetailDTO dto = new CategoryDetailDTO();
        dto.setDescription(updateRequestDTO.getDescription());
        dto.setName(updateRequestDTO.getName());
        dto.setImage(updateRequestDTO.getImage());
        dto.setCreateAt(new Date());
        dto.setId(id);
        return dto;
    }
}
