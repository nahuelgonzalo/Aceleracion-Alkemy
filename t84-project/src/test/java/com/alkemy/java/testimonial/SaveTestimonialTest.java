package com.alkemy.java.testimonial;

import com.alkemy.java.controller.TestimonialController;
import com.alkemy.java.dto.TestimonialDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static com.alkemy.java.testimonial.TestimonialData.*;

@SpringBootTest
public class SaveTestimonialTest {

    @Autowired
    private TestimonialController testimonialController;

    @MockBean
    private TestimonialController mockTestimonialController;

    Set<ConstraintViolation<TestimonialDTO>> violations;
    List<ConstraintViolation> violationsList;
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    @Test
    void saveTestimonial(){
        Mockito.when(mockTestimonialController.saveTestimonial(initialTestimonialDTO()))
                .thenReturn(new ResponseEntity<>(initialTestimonialDTO(), HttpStatus.CREATED));
        ResponseEntity<TestimonialDTO> response = testimonialController.saveTestimonial(initialTestimonialDTO());
        TestimonialDTO testimonialDTO = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(testimonialDTO.getContent()).isEqualTo(INITIAL_CONTENT);
        assertThat(testimonialDTO.getName()).isEqualTo(INITIAL_NAME);
        assertThat(testimonialDTO.getImage()).isEqualTo(INITIAL_IMAGE);
    }

    @Test
    void saveTestimonialWithoutAttributes(){
        TestimonialDTO testimonialDTOWithoutAttributes = new TestimonialDTO();
        Mockito.when(mockTestimonialController.saveTestimonial(testimonialDTOWithoutAttributes))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
        ResponseEntity<TestimonialDTO> response = testimonialController.saveTestimonial(testimonialDTOWithoutAttributes);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        violations = factory.getValidator().validate(testimonialDTOWithoutAttributes);
        assertThat(violations.size()).isEqualTo(2);
    }

    @Test
    void testimonialDTOWithNullName(){
        TestimonialDTO testimonialDTOWithNullName = initialTestimonialDTO();
        testimonialDTOWithNullName.setName(null);
        violations = factory.getValidator().validate(testimonialDTOWithNullName);
        violationsList = new ArrayList<>(violations);
        assertThat(violationsList.get(0).getMessage()).isEqualTo("The field name is required.");
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    void testimonialDTOWithNullContent(){
        TestimonialDTO testimonialDTOWithNullName = initialTestimonialDTO();
        testimonialDTOWithNullName.setContent(null);
        violations = factory.getValidator().validate(testimonialDTOWithNullName);
        violationsList = new ArrayList<>(violations);
        assertThat(violationsList.get(0).getMessage()).isEqualTo("Please enter content, is required.");
        assertThat(violations.size()).isEqualTo(1);
    }

}
