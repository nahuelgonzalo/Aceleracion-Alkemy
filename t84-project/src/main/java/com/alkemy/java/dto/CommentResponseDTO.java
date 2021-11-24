package com.alkemy.java.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CommentResponseDTO {
    private String body;
    private Date createdAt;
    private Date updatedAt;
}
