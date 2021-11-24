package com.alkemy.java.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryRequestDTO implements Serializable {
    @ApiModelProperty(example = "Tecnología", required = true)
    private String name;
    @ApiModelProperty(example = "Categoría que abarca todo lo relacionado a la tecnología.", position = 1)
    private String description;
    @ApiModelProperty(example = "<ImagenEnBase64>", position = 2)
    private String image;

}
