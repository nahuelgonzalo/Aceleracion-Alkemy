package com.alkemy.java.member;

import com.alkemy.java.controller.MemberController;
import com.alkemy.java.dto.MemberRequestDTO;
import com.alkemy.java.dto.MemberResponseDTO;
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
 * Test class for the {@link MemberController#saveMember(MemberRequestDTO)}
 *
 * @author Sergio Villanueva <sergiovillanueva@protonmail.com>
 */
@SpringBootTest
class SaveMemberTest {

    @Autowired
    private MemberController memberController;

    @MockBean
    private MemberController mockMemberController;

    @Test
    void saveMember() {
        Mockito.when(mockMemberController.saveMember(initialMemberRequestDTO()))
                .thenReturn(new ResponseEntity<>(initialMemberResponseDTO(), HttpStatus.CREATED));

        ResponseEntity<MemberResponseDTO> response = memberController.saveMember(initialMemberRequestDTO());
        MemberResponseDTO memberResponseDTO = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(memberResponseDTO.getDescription()).isEqualTo(INITIAL_DESCRIPTION);
        assertThat(memberResponseDTO.getImage()).isEqualTo(INITIAL_IMAGE);
        assertThat(memberResponseDTO.getName()).isEqualTo(INITIAL_NAME);
    }

    @Test
    void saveMemberWithoutAttributes() {
        MemberRequestDTO memberRequestDTOwithoutAttributes = new MemberRequestDTO();
        Mockito.when(mockMemberController.saveMember(memberRequestDTOwithoutAttributes))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));

        ResponseEntity<MemberResponseDTO> response = memberController.saveMember(memberRequestDTOwithoutAttributes);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

}
