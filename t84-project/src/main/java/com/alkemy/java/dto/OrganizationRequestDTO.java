package com.alkemy.java.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationRequestDTO {


    @NotEmpty(message = "el campo no debe ser vacio")
    private String name;
    @NotEmpty(message = "el campo no debe ser vacio")
    private String image;
    private Integer phone;
    private String address;
    @NotEmpty(message = "el campo no debe ser vacio")
    @Email
    private String email;
    @NotEmpty(message = "el campo no debe ser vacio")
    private String welcomeText;
    @NotEmpty(message = "el campo no debe ser vacio")
    private String aboutUsText;
    private String facebookUrl;
    private String linkedinUrl;
    private String instagramUrl;
}
