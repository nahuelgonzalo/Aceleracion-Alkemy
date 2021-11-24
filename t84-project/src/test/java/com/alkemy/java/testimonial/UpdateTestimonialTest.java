package com.alkemy.java.testimonial;

import com.alkemy.java.controller.TestimonialController;
import com.alkemy.java.dto.TestimonialDTO;
import com.alkemy.java.service.TestimonialService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static com.alkemy.java.testimonial.TestimonialData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class UpdateTestimonialTest {

    @Autowired
    private TestimonialController testimonialController;

    @MockBean
    private TestimonialService testimonialService;

    @Test
    void updateTestimonial() {
        Mockito.when(testimonialService.updateTestimonial(initialTestimonialDTO(), INITIAL_ID))
                .thenReturn(updatedTestimonialDTO());

        ResponseEntity<TestimonialDTO> response = testimonialController.updateTestimonial(initialTestimonialDTO(), INITIAL_ID);
        TestimonialDTO testimonialDTO = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(testimonialDTO.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testimonialDTO.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testimonialDTO.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testimonialDTO.getImage()).isEqualTo(UPDATED_IMAGE);

    }
}
