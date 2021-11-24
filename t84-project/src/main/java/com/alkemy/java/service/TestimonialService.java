package com.alkemy.java.service;

import com.alkemy.java.dto.PageDTO;
import com.alkemy.java.dto.TestimonialDTO;
import com.alkemy.java.model.Testimonial;
import org.springframework.http.HttpStatus;

public interface TestimonialService {
    Testimonial saveTestimonial(TestimonialDTO testimonialDTO);

    String deleteTestimonialById(Long id);

    PageDTO<TestimonialDTO> findAll(Integer page);

    TestimonialDTO updateTestimonial(TestimonialDTO testimonialDTO, Long id);
}
