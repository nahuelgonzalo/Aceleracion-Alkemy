package com.alkemy.java.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
public class ActivityRequestDTO {
    @NotEmpty
    @NotNull
    private String name;
    @NotEmpty
    @NotNull
    private String content;
    private String image;
}
