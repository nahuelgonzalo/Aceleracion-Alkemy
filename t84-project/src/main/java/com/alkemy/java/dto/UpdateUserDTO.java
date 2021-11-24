package com.alkemy.java.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateUserDTO {

    @NotBlank(message = "firstName can't cant be empty")
    private String firstName;
    @NotBlank(message = "lastName can't cant be empty")
    private String lastName;
    @NotBlank(message = "photo can't cant be empty")
    private String photo;

}
