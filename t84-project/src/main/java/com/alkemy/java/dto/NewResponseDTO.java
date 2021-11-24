package com.alkemy.java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewResponseDTO {
    private Long id;
    @NotBlank(message = "the name cannot cant be empty")
    private String name;
    @NotBlank(message = "the context cannot cant be empty")
    private String content;
    @NotBlank(message = "the image cannot cant be empty")
    private String image;
    @NotNull(message = "the category cannot cant be empty")
    private CategoryResponseDTO category;
}
