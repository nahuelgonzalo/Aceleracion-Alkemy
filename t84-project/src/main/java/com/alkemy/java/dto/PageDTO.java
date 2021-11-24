package com.alkemy.java.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PageDTO<T> {
    private List<T> content;
    private Integer totalPages;
    private String previousUrl;
    private String nextUrl;
}
