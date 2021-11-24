package com.alkemy.java.controller;

import com.alkemy.java.dto.LoginRequestDTO;
import com.alkemy.java.dto.UserDTO;
import com.alkemy.java.dto.UserResponseDTO;
import com.alkemy.java.dto.UserTokenDTO;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private AuthenticationController authenticationController;

    private UserDTO DTO;
    private ObjectMapper objectMapper;
    private UserTokenDTO userTokenDTO;
    private UserResponseDTO userResponseDTO;
    private LoginRequestDTO loginRequestDTO;


    @BeforeEach
    void setUp(){
        objectMapper = new ObjectMapper();
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        DTO = new UserDTO();
        DTO.setName("test");
        DTO.setEmail("testing@email.com");
        DTO.setPhoto("url");
        DTO.setPassword("12345678Ss");
        DTO.setSurname("lastTest");
        userResponseDTO.setName(DTO.getName());
        userResponseDTO.setEmail(DTO.getEmail());
        userResponseDTO.setSurname(DTO.getSurname());
        userResponseDTO.setPhoto(DTO.getPhoto());
        loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setEmail(DTO.getEmail());
        loginRequestDTO.setPassword(DTO.getPassword());
        userTokenDTO = new UserTokenDTO();
        userTokenDTO.setUserResponseDTO(userResponseDTO);
    }

    @Test
    @Order(1)
    void createAccount() throws Exception {
        when(authenticationController.createAccount(DTO))
                .thenReturn( new ResponseEntity<>(userTokenDTO, HttpStatus.CREATED));
        System.out.println(userTokenDTO.getToken());
        mvc.perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("userDTO.name").value("test"))
                .andExpect(jsonPath("userDTO.email").value("testing@email.com"))
                .andExpect(jsonPath("userDTO.photo").value("url"))
                .andExpect(jsonPath("userDTO.surname").value("lastTest"));
        verify(authenticationController).createAccount(any());
    }

    @Test
    @Order(2)
    void CreateAccountInvalid() throws Exception {
        UserDTO dtoInvalid = new UserDTO();
        when(authenticationController.createAccount(dtoInvalid)).thenReturn(
                new ResponseEntity<>(HttpStatus.BAD_REQUEST)
        );
        mvc.perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoInvalid)))
                .andExpect(status().isBadRequest());
        verify(authenticationController,never()).createAccount(any());
    }

    @Test
    @Order(3)
    void login() throws Exception {
        when(authenticationController.login(loginRequestDTO))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));

        mvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequestDTO)).characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn();
        verify(authenticationController).login(any());
    }

    @Test
    @Order(4)
    void loginInvalid() throws Exception {
        LoginRequestDTO loginInvalid = new LoginRequestDTO();
        mvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginInvalid)))
                .andExpect(status().isBadRequest());
        verify(authenticationController,never()).login(any());
    }

    @Test
    @Order(5)
    @WithMockUser("/testAlkemy")
    void getUserLogged() throws Exception {
        when(authenticationController.getUserLogged()).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        mvc.perform(get("/auth/me"))
                .andExpect(status().isOk())
                .andReturn();
        verify(authenticationController).getUserLogged();
    }
}