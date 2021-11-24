package com.alkemy.java.testimonial;

import com.alkemy.java.controller.TestimonialController;
import com.alkemy.java.dto.PageDTO;
import com.alkemy.java.dto.TestimonialDTO;
import com.alkemy.java.service.TestimonialService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static com.alkemy.java.testimonial.TestimonialData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class GetAllTestimonialTest {

    @Autowired
    private TestimonialController testimonialController;

    @MockBean
    private TestimonialService testimonialService;

    @Test
    void getAllTestimonials() {
        Mockito.when(testimonialService.findAll(1))
                .thenReturn(initialPageDTO());

        PageDTO<TestimonialDTO> pageDTO = testimonialController.getAllTestimonials(1);
        List<TestimonialDTO> testimonialDTOList = pageDTO.getContent();
        TestimonialDTO testimonialDTO = testimonialDTOList.get(0);

        assertThat(testimonialDTOList.size()).isEqualTo(1);
        assertThat(pageDTO.getNextUrl()).isNull();
        assertThat(pageDTO.getPreviousUrl()).isNull();
        assertThat(pageDTO.getTotalPages()).isEqualTo(1);
        assertThat(testimonialDTOList.size()).isEqualTo(1);
        assertThat(testimonialDTO).isEqualTo(initialTestimonialDTO());
    }
}
