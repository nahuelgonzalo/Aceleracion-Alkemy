package com.alkemy.java.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SlideDTO {
    private Long id;
    private String imageUrl;
    private String text;
    private Integer order;
}
