package com.alkemy.java.service;

import com.alkemy.java.dto.SlideRequestDTO;
import com.alkemy.java.dto.SlideResponseDTO;
import java.util.List;
import java.io.IOException;

public interface SlideService {
    SlideRequestDTO addSlide(SlideRequestDTO slide) throws IOException;
    List<SlideResponseDTO> findAllWithJustImageAndOrder();
    SlideResponseDTO findById(Long id);
    void deleteSlide(Long id);
    SlideResponseDTO updateSlide(Long id, SlideRequestDTO slideRequestDto);
}
