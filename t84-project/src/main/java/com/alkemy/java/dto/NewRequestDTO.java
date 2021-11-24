package com.alkemy.java.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewRequestDTO {
    @NotBlank(message = "the name cannot cant be empty")
    @ApiModelProperty(example = "Algun nombre descriptivo", required = true)
    private String name;
    @NotBlank(message = "the context cannot cant be empty")
    @ApiModelProperty(example = "Esto es el contenido de la New", required = true)
    private String content;
    @NotBlank(message = "the image cannot cant be empty")
    @ApiModelProperty(example = "<ImagenEnBase64>", required = true)
    private String image;
    @NotNull(message = "the category cannot cant be empty")
    @ApiModelProperty(example = "1", required = true)
    private Long idCategory;

}
