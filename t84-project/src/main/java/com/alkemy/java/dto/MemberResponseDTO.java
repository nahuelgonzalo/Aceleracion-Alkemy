package com.alkemy.java.dto;

import lombok.Data;

@Data
public class MemberResponseDTO {
    private Long id;
    private String name;
    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;
    private String image;
    private String description;
}
