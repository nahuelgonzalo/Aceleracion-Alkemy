package com.alkemy.java.category;

import com.alkemy.java.controller.CategoryController;
import com.alkemy.java.dto.*;
import com.alkemy.java.service.CategoryService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.*;
import static com.alkemy.java.category.CategoryTestUtil.*;

@SpringBootTest
class CategoryControllerTest {

    @MockBean
    public CategoryService categoryServiceMock;

    @Autowired
    private CategoryController categoryController;

    @Test
    void getCategoryById() {
        //when
        Mockito.when(categoryServiceMock.findDTOById(SECOND_ID))
                .thenReturn(getDetailDTO(SECOND_ID));
        //then
        ResponseEntity<CategoryDetailDTO> response = categoryController.getCategoryById(SECOND_ID);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void addCategory() {
        //when
        Mockito.when(categoryServiceMock.addCategory(getFirstRequestDTO()))
                .thenReturn(getFirstDTO());
        //then
        ResponseEntity<CategoryDTO> response = categoryController.addCategory(getFirstRequestDTO());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void findAllPageable() {
        //when
        Integer page = 1;
        Mockito.when(categoryServiceMock.findAll(page)).thenReturn(getPageDTO());
        //then
        ResponseEntity<PageDTO<CategoryDTO>> response = categoryController.findAllPageable(page);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updateCategory() {
        //when
        Mockito.when(categoryServiceMock.updateCategory(getUpdateRequestDTO(),FIRST_ID))
                .thenReturn(getUpdateDTO(FIRST_ID));
        //then
        ResponseEntity<CategoryDTO> response = categoryController.updateCategory(getUpdateRequestDTO(),FIRST_ID);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void deleteCategory() {
        //when
        Mockito.doNothing().when(categoryServiceMock).deleteCategory(FIRST_ID);
        //then
        ResponseEntity<Void> response = categoryController.deleteCategory(FIRST_ID);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}