package com.alkemy.java.testimonial;

import com.alkemy.java.dto.PageDTO;
import com.alkemy.java.dto.TestimonialDTO;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;

@SpringBootTest
public class TestimonialData {

    public static final long INITIAL_ID = 1L;
    public static final String INITIAL_NAME = "Testimonial";
    public static final String INITIAL_CONTENT = "Content";
    public static final String INITIAL_IMAGE = "Image.png";
    public static final Date INITIAL_CREATED_AT = Date.from(Instant.EPOCH);
    public static final Date INITIAL_MODIFIED_AT = Date.from(Instant.EPOCH);
    
    public static final String UPDATED_NAME = "Updated Testimonial";
    public static final String UPDATED_CONTENT = "Updated content";
    public static final String UPDATED_IMAGE = "UpdatedImage.png";
    public static final Date UPDATED_MODIFIED_AT = Date.from(Instant.now());

    public static final int INITIAL_TOTALPAGES = 1;
    public static final long NONEXISTENT_ID = 402859203L;
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public static TestimonialDTO initialTestimonialDTO() {
        TestimonialDTO testimonialDTO = new TestimonialDTO();
        testimonialDTO.setName(INITIAL_NAME);
        testimonialDTO.setContent(INITIAL_CONTENT);
        testimonialDTO.setImage(INITIAL_IMAGE);
        return testimonialDTO;
    }

    public static PageDTO<TestimonialDTO> initialPageDTO() {
        TestimonialDTO testimonialDTO = initialTestimonialDTO();
        PageDTO<TestimonialDTO> pageDTO = new PageDTO<>();
        pageDTO.setContent(Collections.singletonList(testimonialDTO));
        pageDTO.setTotalPages(INITIAL_TOTALPAGES);
        return pageDTO;
    }

    public static TestimonialDTO updatedTestimonialDTO() {
        TestimonialDTO testimonialDTO = new TestimonialDTO();
        testimonialDTO.setName(UPDATED_NAME);
        testimonialDTO.setContent(UPDATED_CONTENT);
        testimonialDTO.setImage(UPDATED_IMAGE);
        return testimonialDTO;
    }
}
