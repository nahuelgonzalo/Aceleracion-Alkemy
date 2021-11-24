package com.alkemy.java.member;

import com.alkemy.java.controller.MemberController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.alkemy.java.member.MemberData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Test class for the {@link MemberController#deleteById(Long)}
 *
 * @author Sergio Villanueva <sergiovillanueva@protonmail.com>
 */
@SpringBootTest
class DeleteMemberTest {

    @Autowired
    private MemberController memberController;

    @MockBean
    private MemberController mockMemberController;

    @Test
    void deleteMember() {
        Mockito.when(mockMemberController.deleteById(INITIAL_ID))
                .thenReturn(new ResponseEntity<>("deleted", HttpStatus.NO_CONTENT));

        ResponseEntity<String> response = memberController.deleteById(INITIAL_ID);
        String message = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(message.length()).isGreaterThan(1);
    }

    @Test
    void deleteMemberWithNonexistentId() {
        Mockito.when(mockMemberController.deleteById(NONEXISTENT_ID))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

        ResponseEntity<String> response = memberController.deleteById(NONEXISTENT_ID);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
