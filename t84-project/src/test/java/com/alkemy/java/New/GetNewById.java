package com.alkemy.java.New;

import com.alkemy.java.controller.NewController;
import com.alkemy.java.dto.NewResponseDTO;
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
class GetNewById {

    @Autowired
    private NewController newController;

    @MockBean
    private NewService newService;

    @Test
    void getNewById() {
        Mockito.when(newService.findById(INITIAL_ID)).thenReturn(initialNewDTO());

        ResponseEntity<NewResponseDTO> newResponseDTO = newController.getNewById(INITIAL_ID);

        assertThat(newResponseDTO.getBody().getId()).isEqualTo(INITIAL_ID);
        assertThat(newResponseDTO.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}