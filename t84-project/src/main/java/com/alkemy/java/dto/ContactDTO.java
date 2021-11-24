package com.alkemy.java.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class ContactDTO {
    private Long id;
    @NotEmpty
    private String name;
    private String phone;
    @NotEmpty
    @Email
    private String email;
    private String message;
}
