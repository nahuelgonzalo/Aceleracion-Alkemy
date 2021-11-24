package com.alkemy.java.New;

import com.alkemy.java.controller.NewController;
import com.alkemy.java.dto.CommentResponseDTO;
import com.alkemy.java.service.NewService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.alkemy.java.New.NewData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class getListComments {

    @Autowired
    private NewController newController;

    @MockBean
    private NewService newService;

    @Test
    void getListComments() {

        CommentResponseDTO comment = new CommentResponseDTO();
        comment.setBody("body");
        comment.setCreateAt(new Date());
        comment.setUpdateAt(new Date());
        List<CommentResponseDTO> commentResponseDTOS = new ArrayList<>();
        commentResponseDTOS.add(comment);

        Mockito.when(newService.listComment(INITIAL_ID)).thenReturn(commentResponseDTOS);

        ResponseEntity<List<CommentResponseDTO>> comments= newController.getListComments(INITIAL_ID);

        assertThat(comments.getBody()).isEqualTo(commentResponseDTOS);
        assertThat(comments.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}