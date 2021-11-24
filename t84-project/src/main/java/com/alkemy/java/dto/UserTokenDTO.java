package com.alkemy.java.dto;

import lombok.Data;

@Data
public class UserTokenDTO {
    private UserResponseDTO userResponseDTO;
    private String token;
}
