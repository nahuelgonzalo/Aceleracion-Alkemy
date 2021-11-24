package com.alkemy.java.dto;

import com.alkemy.java.model.Role;
import lombok.Data;

import java.util.Date;

@Data
public class UserAuthDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String photo;
    private Role role;
    private Date createAt;
    private Boolean deleted;
}
