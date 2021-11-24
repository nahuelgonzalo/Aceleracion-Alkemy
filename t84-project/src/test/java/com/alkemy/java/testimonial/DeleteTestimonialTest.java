package com.alkemy.java.testimonial;

import com.alkemy.java.controller.TestimonialController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static com.alkemy.java.testimonial.TestimonialData.*;

@SpringBootTest
public class DeleteTestimonialTest {

    @Autowired
    private TestimonialController testimonialController;

    @MockBean
    private TestimonialController mockTestimonialController;

    @Test
    void deleteTestimonial() {
        Mockito.when(mockTestimonialController.deleteTestimonial(INITIAL_ID))
                .thenReturn(new ResponseEntity<>("Testimonial deleted succesfully", HttpStatus.NO_CONTENT));
        ResponseEntity<String> response = testimonialController.deleteTestimonial(INITIAL_ID);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isEqualTo("Testimonial deleted succesfully");
    }

    @Test
    void deleteTestimonialWithNonexistentId() {
        Mockito.when(mockTestimonialController.deleteTestimonial(NONEXISTENT_ID))
                .thenReturn(new ResponseEntity<>("Testimonial with id "+ NONEXISTENT_ID +" not found", HttpStatus.NOT_FOUND));
        ResponseEntity<String> response = testimonialController.deleteTestimonial(NONEXISTENT_ID);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isEqualTo("Testimonial with id "+ NONEXISTENT_ID +" not found");
    }
}
