package com.alkemy.java.mapper;

import com.alkemy.java.dto.TestimonialDTO;
import com.alkemy.java.model.Testimonial;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestimonialMapper {
    Testimonial toTestimonial(TestimonialDTO testimonialDTO);
    TestimonialDTO toDto(Testimonial testimonial);
}
