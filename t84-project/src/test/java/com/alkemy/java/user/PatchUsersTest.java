package com.alkemy.java.user;

import com.alkemy.java.controller.UserController;

import com.alkemy.java.dto.UpdateUserDTO;
import com.alkemy.java.dto.UserDTO;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PatchUsersTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserController mockUserController;
    private UpdateUserDTO updateUserDTO;
    private ObjectMapper objectMapper;
    private UserDTO userDto;
    static final long INITIAL_ID=1L;
    @BeforeEach
    public void setUpDTO(){

        objectMapper = new ObjectMapper();
        updateUserDTO=new UpdateUserDTO();
        userDto =new UserDTO();
        userDto.setPhoto("requestPhoto");
        userDto.setName("requestName");
        userDto.setSurname("requestLastName");
        userDto.setEmail("EmailResponse");
        userDto.setPassword("asd");
        updateUserDTO.setPhoto("requestPhoto");
        updateUserDTO.setFirstName("requestName");
        updateUserDTO.setLastName("requestLastName");

    }
    @Test
    @Order(1)
    @WithMockUser("/test")
    public void pathUsersTest() throws Exception {
        when(mockUserController.updateUser(updateUserDTO, INITIAL_ID))
                .thenReturn(new ResponseEntity<>(userDto, HttpStatus.OK));
        mvc.perform(patch("/users/" + INITIAL_ID).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateUserDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("requestName"))
                .andExpect(jsonPath("surname").value("requestLastName"))
                .andExpect(jsonPath("photo").value("requestPhoto"));
    }
}