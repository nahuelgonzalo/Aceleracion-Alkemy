package com.alkemy.java.user.scenary;

import com.alkemy.java.dto.UserResponseDTO;
import com.alkemy.java.model.User;
import lombok.Data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
@Data
public class UniteTestEmptyUsers {
    ArrayList<UserResponseDTO> userArrayList;
    @BeforeEach
    public void setUserArrayListBefore() {
        userArrayList=new ArrayList<>();
    }
    @AfterEach
    public void setUserArrayListAfter() {
        userArrayList=new ArrayList<>();
    }

}
