package com.alkemy.java.dto;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CommentAllFieldsDTO {

    @NotEmpty(message = "No se puede agregar un comentario vacio")
    private String body;
    @NotNull(message = "Novedad debe ser distinto de null")
    private Long newsId;
}

