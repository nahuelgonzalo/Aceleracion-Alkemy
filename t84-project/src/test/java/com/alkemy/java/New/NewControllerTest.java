package com.alkemy.java.New;

import com.alkemy.java.controller.NewController;
import com.alkemy.java.dto.NewResponseDTO;
import com.alkemy.java.dto.PageDTO;
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

import java.util.List;


@SpringBootTest
class NewControllerTest {

    @Autowired
    private NewController newController;

    @MockBean
    private NewService newService;

    @Test
    void getAll() {
        Mockito.when(newService.findAll(1))
                .thenReturn(newPageDTO());

        ResponseEntity<PageDTO<NewResponseDTO>> pageDTO = newController.getAll(1);
        List<NewResponseDTO> newDTOList = pageDTO.getBody().getContent();
        NewResponseDTO newResponseDTO = newDTOList.get(0);

        assertThat(pageDTO.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(newDTOList.size()).isEqualTo(1);
        assertThat(pageDTO.getBody().getNextUrl()).isNull();
        assertThat(pageDTO.getBody().getPreviousUrl()).isNull();
        assertThat(pageDTO.getBody().getTotalPages()).isEqualTo(1);
        assertThat(newDTOList.size()).isEqualTo(1);
        assertThat(newResponseDTO).isEqualTo(initialNewDTO());
    }
}