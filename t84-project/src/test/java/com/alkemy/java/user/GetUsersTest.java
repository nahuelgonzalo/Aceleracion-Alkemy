package com.alkemy.java.user;

import com.alkemy.java.controller.UserController;
import com.alkemy.java.dto.UserResponseDTO;
import com.alkemy.java.model.User;
import com.alkemy.java.service.UserService;
import com.alkemy.java.user.scenary.UnitTestWithUsers;
import com.alkemy.java.user.scenary.UniteTestEmptyUsers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

@SpringBootTest
public class GetUsersTest {
    private UnitTestWithUsers unitTestWithUsers =new UnitTestWithUsers();
    private UniteTestEmptyUsers unitTestEmptyUsers =new UniteTestEmptyUsers();
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUnitTestWithUsersBefore(){
        unitTestWithUsers.setUserArrayListBefore();
    }
    @AfterEach
    public void setUnitTestWithUsersAfter(){
        unitTestWithUsers.setUserArrayListAfter();
    }
    @BeforeEach
    public void setUnitTestEmptyUsersUsersBefore(){
        unitTestEmptyUsers.setUserArrayListBefore();
    }
    @AfterEach
    public void setUnitTestEmptyUsersUsersAfter(){
        unitTestEmptyUsers.setUserArrayListAfter();
    }

    @Test
    public void getUsersControllerWithUsers(){
        ArrayList<UserResponseDTO> users = unitTestWithUsers.getUserArrayList();
        Mockito.when(userService.findAll()).thenReturn(users);
        ArrayList<User> userArrayList;
        ResponseEntity responseEntity=userController.getUsers();
        userArrayList= (ArrayList<User>) responseEntity.getBody();
        Assertions.assertArrayEquals(userArrayList.toArray(),users.toArray());
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

  @Test
    public void getUsersControllerEmptyUsers(){
        ArrayList<UserResponseDTO> users = unitTestEmptyUsers.getUserArrayList();
        Mockito.when(userService.findAll()).thenReturn(users);
        ArrayList<User> userArrayList;
        ResponseEntity responseEntity=userController.getUsers();
        userArrayList= (ArrayList<User>) responseEntity.getBody();
         Assertions.assertArrayEquals(userArrayList.toArray(),users.toArray());
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

}
