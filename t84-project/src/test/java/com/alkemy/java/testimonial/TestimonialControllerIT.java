package com.alkemy.java.testimonial;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.alkemy.java.testimonial.TestimonialData.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestimonialControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(authorities = ROLE_USER)
    void getMembersAsRoleUser() throws Exception {
        mockMvc
                .perform(get("/testimonials").accept(APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(authorities = ROLE_ADMIN)
    void getMembersAsRoleAdmin() throws Exception {
        mockMvc
                .perform(get("/testimonials").accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
