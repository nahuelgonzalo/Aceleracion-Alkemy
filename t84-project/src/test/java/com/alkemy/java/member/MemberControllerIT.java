package com.alkemy.java.member;

import com.alkemy.java.controller.MemberController;
import com.alkemy.java.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.alkemy.java.member.MemberData.ROLE_USER;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test class for the {@link MemberController}
 *
 * @author Sergio Villanueva <sergiovillanueva@protonmail.com>
 */
@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @WithMockUser(authorities = ROLE_USER)
    void getMembersAsRoleUser() throws Exception {
        mockMvc
                .perform(get("/members").accept(APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(authorities = ROLE_USER)
    void deleteMemberAsRoleUser() throws Exception {
        mockMvc
                .perform(delete("/members/1").accept(APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

}
