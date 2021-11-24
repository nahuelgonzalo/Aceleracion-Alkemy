package com.alkemy.java.user;

import com.alkemy.java.controller.UserController;

import com.alkemy.java.dto.UserDTO;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static com.alkemy.java.testimonial.TestimonialData.NONEXISTENT_ID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;


@SpringBootTest
public class DeleteUsersTest {

    private MockMvc mvc;
    @MockBean
    private UserController mockuserController;

    @Autowired
    private UserController userController;
    private UserDTO userDTO;
    static final long INITIAL_ID=1L;
    @Test
    @WithMockUser("/test")
    public void deleteController(){
        when(mockuserController.deleteUsers(INITIAL_ID)).thenReturn(new ResponseEntity<>("deleted", HttpStatus.NO_CONTENT));
        ResponseEntity<String> response = userController.deleteUsers(INITIAL_ID);
        String message = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(message.length()).isGreaterThan(1);
    }

    @Test
    @WithMockUser("/test")
    public void deleteControllerBadId(){
        Mockito.when(mockuserController.deleteUsers(NONEXISTENT_ID))
                .thenReturn(new ResponseEntity<>("User with id "+ NONEXISTENT_ID +" not found", HttpStatus.NOT_FOUND));
        ResponseEntity<String> response = userController.deleteUsers(NONEXISTENT_ID);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isEqualTo("User with id "+ NONEXISTENT_ID +" not found");
    }
}
