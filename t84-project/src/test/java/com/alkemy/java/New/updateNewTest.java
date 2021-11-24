package com.alkemy.java.New;

import com.alkemy.java.controller.NewController;
import com.alkemy.java.dto.CategoryResponseDTO;
import com.alkemy.java.dto.NewRequestDTO;
import com.alkemy.java.dto.NewResponseDTO;
import com.alkemy.java.model.Category;
import com.alkemy.java.service.NewService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.alkemy.java.New.NewData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class updateNewTest {

    @Autowired
    private NewController newController;

    @MockBean
    private NewService newService;

    @Test
    void updateNew() {
        NewRequestDTO initialNewDTO = new NewRequestDTO();

        CategoryResponseDTO category = new CategoryResponseDTO();

        Mockito.when(newService.updateNew(initialNewDTO,INITIAL_ID)).thenReturn(updatedNewDTO());

        ResponseEntity<NewResponseDTO> newResponseDTO = newController.updateNew(initialNewDTO,INITIAL_ID);

        assertThat(newResponseDTO.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(newResponseDTO.getBody().getName()).isEqualTo(UPDATED_NAME);
        assertThat(newResponseDTO.getBody().getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(newResponseDTO.getBody().getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(newResponseDTO.getBody().getCategory()).isEqualTo(categoryResponse);
    }
}