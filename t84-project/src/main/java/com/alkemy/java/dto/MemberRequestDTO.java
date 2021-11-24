package com.alkemy.java.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel(description = "Class representing a member in the application.")
@Data
@NoArgsConstructor
public class MemberRequestDTO {
    @ApiModelProperty(notes = "Name the menber.",
            example = "Juan", required = true, position = 1)
    @NotBlank
    @Size(max = 100)
    private String name;

    @ApiModelProperty(notes = "URL the facebook.",
            example = "www.facebook.com/username", required = false, position = 2)
    private String facebookUrl;

    @ApiModelProperty(notes = "URL the instagram.",
            example = "www.instragram.com/username", required = false, position = 3)
    private String instagramUrl;

    @ApiModelProperty(notes = "URL the linkedin.",
            example = "www.linkedin.com/in/nickname/", required = false, position = 4)
    private String linkedinUrl;

    @ApiModelProperty(notes = "Image the menber.",
            example = "<ImagenEnBase64>", required = true, position = 5)
    @NotBlank
    @Size(max = 100)
    private String image;

    @ApiModelProperty(notes = "Description the menber",
            example = "the member is a worker", required = false, position = 6)
    private String description;
}
