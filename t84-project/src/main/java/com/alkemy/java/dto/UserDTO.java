package com.alkemy.java.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

@ApiModel(description = "Class representing a user in the application.")
@Data
public class UserDTO {

    @ApiModelProperty(notes = "User name.",
            example = "Juan", required = true, position = 0)
    @NotEmpty(message = "el campo no debe ser vacio")
    private String name;

    @ApiModelProperty(notes = "User surname.",
            example = "Perez", required = true, position = 1)
    @NotEmpty(message = "el campo no debe ser vacio")
    private String surname;

    @ApiModelProperty(notes = "User email.",
            example = "juan.perez@gmail.com", required = true, position = 2)
    @Email
    private String email;

    @ApiModelProperty(notes = "User photo.",
            example = "<ImagenEnBase64>", required = false, position = 4)
    private String photo;

    @ApiModelProperty(notes = "User password.",
            example = "myPassword1234", required = true, position = 3)
    @Pattern(regexp = "^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$",
            message = "La contraseña debe tener al entre 8 y 16 caracteres, al menos un dígito, al menos una minúscula y al menos una mayúscula")
    private String password;

}
