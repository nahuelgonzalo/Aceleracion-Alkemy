package com.alkemy.java.user.scenary;

import com.alkemy.java.dto.IDTOQuery.IUserDTO;
import com.alkemy.java.dto.UserResponseDTO;
import com.alkemy.java.model.Role;
import com.alkemy.java.model.User;

import lombok.Data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Calendar;

@Data
public class UnitTestWithUsers {
    
    ArrayList<UserResponseDTO> userArrayList;
    @BeforeEach
    public void setUserArrayListBefore(){
        userArrayList=new ArrayList<UserResponseDTO>();
        UserResponseDTO user,user1;

        user= new UserResponseDTO();
        user.setName("User");
        user.setPhoto("User 1 photo");
        user.setSurname("1");
        user.setEmail("User 1 Email");

        user1= new UserResponseDTO();
        user1.setName("User");
        user1.setPhoto("User 2 photo");
        user1.setSurname("2");
        user1.setEmail("User 2 Email");


        userArrayList.add(user);
        userArrayList.add(user1);

    }

    @AfterEach
   public void setUserArrayListAfter(){
        userArrayList.clear();
    }

}
