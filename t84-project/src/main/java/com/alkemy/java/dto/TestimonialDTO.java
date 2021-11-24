package com.alkemy.java.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
public class TestimonialDTO {
    @ApiModelProperty(notes = "The field name is required", example = "Testimonial of activity ...", required = true)
    @NotBlank(message ="The field name is required.")
    private String name;
    @ApiModelProperty(notes = "The field content is required",
            example = "The list of content testimonial",
            required = true)
    @NotBlank(message = "Please enter content, is required.")
    private String content;
    @ApiModelProperty(notes = "Select one image", example = "<ImagenEnBase64>")
    private String image;
}
