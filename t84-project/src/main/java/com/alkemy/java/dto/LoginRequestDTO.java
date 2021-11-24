package com.alkemy.java.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {
    @ApiModelProperty(notes = "User email.",
            example = "juan.perez@gmail.com", required = true, position = 0)
    @NotEmpty(message = "Email is required")
    private String email;

    @ApiModelProperty(notes = "User password.",
            example = "myPassword1234", required = true, position = 1)
    @NotEmpty(message = "Password is required")
    private String password;
}
