package com.alkemy.java.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class OrganizationDetailDTO extends OrganizationDTO {
    private List<SlideDTO> slides;
}
