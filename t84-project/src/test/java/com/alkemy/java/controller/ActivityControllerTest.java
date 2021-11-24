package com.alkemy.java.controller;

import com.alkemy.java.dto.ActivityDTO;
import com.alkemy.java.dto.ActivityRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
class ActivityControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ActivityController activityController;
    private ActivityDTO activityDTO;
    private ObjectMapper objectMapper;
    private ActivityRequestDTO activityRequestDTO;
    private final long id = 1;
    private final long invalidId = 12309123;

    @BeforeEach
    void setUpDTO() {
        objectMapper = new ObjectMapper();
        activityDTO = new ActivityDTO();
        activityDTO.setName("activityTest");
        activityDTO.setContent("contentTest");
        activityDTO.setImage("imageTest");
    }

    @BeforeEach
    void setUpResponse() {
        objectMapper = new ObjectMapper();
        activityRequestDTO = new ActivityRequestDTO();
        activityRequestDTO.setName("requestName");
        activityRequestDTO.setContent("requestContent");
        activityRequestDTO.setImage("requestImage");
    }

    @Test
    @Order(1)
    @WithMockUser("/test")
    void createActivity() throws Exception {
        when(activityController.createActivity(activityDTO))
                .thenReturn(new ResponseEntity<>(activityDTO, HttpStatus.CREATED));

        mvc.perform(post("/activities").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(activityDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name").value("activityTest"))
                .andExpect(jsonPath("content").value("contentTest"))
                .andExpect(jsonPath("image").value("imageTest"));
    }

    @Test
    @Order(2)
    @WithMockUser("/test")
    void createActivityInvalid() throws Exception {
        ActivityDTO dtoInvalid = new ActivityDTO();

        when(activityController.createActivity(dtoInvalid)).thenReturn(
                new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        mvc.perform(post("/activities").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dtoInvalid)))
                .andExpect(status().isBadRequest());
        verify(activityController).createActivity(any());
    }

    @Test
    @Order(3)
    @WithMockUser("/test")
    void updateActivity() throws Exception {

        when(activityController.updateActivity(activityRequestDTO, id))
                .thenReturn(new ResponseEntity<>(activityRequestDTO, HttpStatus.OK));

        mvc.perform(put("/activities/" + id).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(activityRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("requestName"))
                .andExpect(jsonPath("content").value("requestContent"))
                .andExpect(jsonPath("image").value("requestImage"));
    }

    @Test
    @Order(4)
    @WithMockUser("/test")
    void updateActivityInvalid() throws Exception {
        ActivityRequestDTO invalidRequest = new ActivityRequestDTO();

        when(activityController.updateActivity(invalidRequest, id))
                .thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        mvc.perform(put("/activities/" + invalidId).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
        verify(activityController, never()).updateActivity(any(), any());
    }
}