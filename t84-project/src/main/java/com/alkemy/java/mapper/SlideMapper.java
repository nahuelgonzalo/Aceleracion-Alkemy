package com.alkemy.java.mapper;

import com.alkemy.java.dto.SlideDTO;
import com.alkemy.java.dto.SlideRequestDTO;
import com.alkemy.java.dto.SlideResponseDTO;
import com.alkemy.java.model.Slide;
import com.alkemy.java.service.OrganizationService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrganizationService.class})
public interface SlideMapper {

    @Mapping(source = "slide.organization.id", target = "organizationId")
    SlideRequestDTO slideToSlideRequestDto(Slide slide);
    
    @Mapping(source = "organizationId", target = "organization")
    Slide toSlide(SlideRequestDTO slideRequestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", ignore = true)
    @Mapping(target = "organization", ignore = true)
    SlideResponseDTO toDTOJustImageAndOrder(Slide slide);

    List<SlideDTO> toSlideImageDTO(List<Slide> slide);
    
    SlideResponseDTO slideToSlideResponseDto(Slide slide);
}
