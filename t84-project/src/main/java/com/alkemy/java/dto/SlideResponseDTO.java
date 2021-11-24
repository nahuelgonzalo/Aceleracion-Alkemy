package com.alkemy.java.dto;

import com.alkemy.java.model.Organization;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SlideResponseDTO {
    private Long id;
    private String imageUrl;
    private String text;
    private String order;
    private Organization organization;
}
