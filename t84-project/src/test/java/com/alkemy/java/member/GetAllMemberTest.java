package com.alkemy.java.member;

import com.alkemy.java.controller.MemberController;
import com.alkemy.java.dto.MemberResponseDTO;
import com.alkemy.java.dto.PageDTO;
import com.alkemy.java.service.MemberService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static com.alkemy.java.member.MemberData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Test class for the {@link MemberController#getAllMembers(Integer)}
 *
 * @author Sergio Villanueva <sergiovillanueva@protonmail.com>
 */
@SpringBootTest
class GetAllMemberTest {

    @Autowired
    private MemberController memberController;

    @MockBean
    private MemberService memberService;

    @Test
    void getAllMembers() {
        Mockito.when(memberService.findAll(1))
                .thenReturn(initialPageDTO());

        PageDTO<MemberResponseDTO> pageDTO = memberController.getAllMembers(1);
        List<MemberResponseDTO> memberDTOList = pageDTO.getContent();
        MemberResponseDTO memberResponseDTO = memberDTOList.get(0);

        assertThat(memberDTOList.size()).isEqualTo(1);
        assertThat(pageDTO.getNextUrl()).isNull();
        assertThat(pageDTO.getPreviousUrl()).isNull();
        assertThat(pageDTO.getTotalPages()).isEqualTo(1);
        assertThat(memberDTOList.size()).isEqualTo(1);
        assertThat(memberResponseDTO).isEqualTo(initialMemberResponseDTO());
    }

}
