package com.alkemy.java.member;

import com.alkemy.java.controller.MemberController;
import com.alkemy.java.dto.MemberRequestDTO;
import com.alkemy.java.dto.MemberResponseDTO;
import com.alkemy.java.service.MemberService;
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
 * Test class for the {@link MemberController#updateMember(MemberRequestDTO, Long)}
 *
 * @author Sergio Villanueva <sergiovillanueva@protonmail.com>
 */
@SpringBootTest
class UpdateMemberTest {

    @Autowired
    private MemberController memberController;

    @MockBean
    private MemberService memberService;

    @Test
    void updateMember() {
        Mockito.when(memberService.updateMember(toUpdateMemberRequestDTO(), INITIAL_ID))
                        .thenReturn(updatedMemberRequestDTO());

        ResponseEntity<MemberResponseDTO> response = memberController.updateMember(toUpdateMemberRequestDTO(), INITIAL_ID);
        MemberResponseDTO memberResponseDTO = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(memberResponseDTO.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(memberResponseDTO.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(memberResponseDTO.getName()).isEqualTo(UPDATED_NAME);
    }

}
