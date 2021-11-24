package com.alkemy.java.controller;

import com.alkemy.java.dto.ContactDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ContactControllerTest {

    @MockBean
    private ContactController contactController;
    @Autowired
    private MockMvc mvcTest;
    @Autowired
    private WebApplicationContext context;
    private ObjectMapper objectMapper;
    private ContactDTO contactDTO;
    private ContactDTO contactDTO2;
    private List<ContactDTO> contactDTOList;

    @BeforeEach
    void setUp(){
        contactDTO = new ContactDTO();
        contactDTO2 = new ContactDTO();
        objectMapper = new ObjectMapper();
        contactDTOList = new ArrayList<>();
        mvcTest = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        contactDTO.setId(1L);
        contactDTO.setName("test");
        contactDTO.setEmail("test@email.com");
        contactDTO.setPhone("1111222234");
        contactDTO.setMessage("Message test");
        contactDTO2.setId(2L);
        contactDTO2.setName("test2");
        contactDTO2.setEmail("test2@email.com");
        contactDTO2.setPhone("2222111194");
        contactDTO2.setMessage("Message test");
        contactDTOList.add(contactDTO);
        contactDTOList.add(contactDTO2);
    }

    @Test
    @WithMockUser("/test")
    void saveContact() throws Exception {
        when(contactController.saveContact(any())).thenReturn(new ResponseEntity<>(contactDTO,HttpStatus.OK));
        mvcTest.perform(post("/contacts/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contactDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("test"))
                .andExpect(jsonPath("email").value("test@email.com"))
                .andExpect(jsonPath("phone").value("1111222234"))
                .andExpect(jsonPath("message").value("Message test"));
        verify(contactController).saveContact(any());
    }

    @Test
    void saveContact403() throws Exception{
        when(contactController.saveContact(any())).thenReturn(new ResponseEntity<>(HttpStatus.FORBIDDEN));
        mvcTest.perform(post("/contacts/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
        verify(contactController,never()).saveContact(any());
    }

    @Test
    @WithMockUser("/test")
    void saveContactInvalid() throws Exception{
        when(contactController.saveContact(any())).thenReturn(new ResponseEntity<>(null,HttpStatus.BAD_REQUEST));
        mvcTest.perform(post("/contacts/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        verify(contactController,never()).saveContact(any());
    }

    @Test
    @WithMockUser("/test")
    void findAllContacts() throws Exception{
        when(contactController.findAllContacts()).thenReturn(
                new ResponseEntity<>(contactDTOList,HttpStatus.OK));
        mvcTest.perform(get("/contacts/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contactDTOList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("test"))
                .andExpect(jsonPath("[0].email").value("test@email.com"))
                .andExpect(jsonPath("[1].name").value("test2"))
                .andExpect(jsonPath("[1].email").value("test2@email.com"));
        verify(contactController).findAllContacts();
    }

    @Test
    void findAllContacts403() throws Exception{
        when(contactController.findAllContacts()).thenReturn(new ResponseEntity<>(HttpStatus.FORBIDDEN));
        mvcTest.perform(get("/contacts/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
        verify(contactController,never()).findAllContacts();
    }
}