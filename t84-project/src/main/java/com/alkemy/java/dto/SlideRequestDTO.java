package com.alkemy.java.dto;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class SlideRequestDTO {

    private String imageUrl;

    private String text;

    @Positive
    private Integer order;

    private Long organizationId;
}
