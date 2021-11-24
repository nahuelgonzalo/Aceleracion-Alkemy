package com.alkemy.java.New;

import com.alkemy.java.controller.NewController;
import com.alkemy.java.dto.CategoryResponseDTO;
import com.alkemy.java.dto.NewRequestDTO;
import com.alkemy.java.dto.NewResponseDTO;
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
class SaveNew {

    @Autowired
    private NewController newController;

    @MockBean
    private NewController mockNewController;

    @Test
    void saveNew() {
        CategoryResponseDTO category = new CategoryResponseDTO();

        NewRequestDTO initialNewDTO2 = new NewRequestDTO();
        initialNewDTO2.setName(INITIAL_NAME);
        initialNewDTO2.setContent(INITIAL_CONTENT);
        initialNewDTO2.setImage(INITIAL_IMAGE);
        initialNewDTO2.setIdCategory(INITIAL_ID);

        Mockito.when(mockNewController.saveNew(initialNewDTO2)).thenReturn(new ResponseEntity<>(initialNewDTO(), HttpStatus.CREATED));

        ResponseEntity<NewResponseDTO> newResponseDTO = newController.saveNew(initialNewDTO2);

        assertThat(newResponseDTO.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(newResponseDTO.getBody().getName()).isEqualTo(INITIAL_NAME);
        assertThat(newResponseDTO.getBody().getImage()).isEqualTo(INITIAL_IMAGE);
        assertThat(newResponseDTO.getBody().getContent()).isEqualTo(INITIAL_CONTENT);
        assertThat(newResponseDTO.getBody().getCategory()).isEqualTo(categoryResponse);
    }
}