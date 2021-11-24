package com.alkemy.java.New;

import com.alkemy.java.controller.NewController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.alkemy.java.New.NewData.INITIAL_ID;
import static com.alkemy.java.testimonial.TestimonialData.NONEXISTENT_ID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class deleteNewsByIdTest {

    @Autowired
    private NewController newController;

    @MockBean
    private NewController mockNewController;

    @Test
    void deleteNewsById() {
        Mockito.when(mockNewController.deleteNewsById(INITIAL_ID)).thenReturn(new ResponseEntity<>("News deleted successfully", HttpStatus.NO_CONTENT));

        ResponseEntity<String> response = newController.deleteNewsById(INITIAL_ID);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isEqualTo("News deleted successfully");
    }
    @Test
    void deleteNewsByIdWithNonexistentId() {
        Mockito.when(mockNewController.deleteNewsById(NONEXISTENT_ID)).thenReturn(new ResponseEntity<>("New with id "+ NONEXISTENT_ID +" not found", HttpStatus.NOT_FOUND));

        ResponseEntity<String> response = newController.deleteNewsById(NONEXISTENT_ID);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isEqualTo("New with id "+ NONEXISTENT_ID +" not found");
    }
}