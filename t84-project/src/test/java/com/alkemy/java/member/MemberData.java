package com.alkemy.java.member;

import com.alkemy.java.dto.MemberRequestDTO;
import com.alkemy.java.dto.MemberResponseDTO;
import com.alkemy.java.dto.PageDTO;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Collections;

/**
 * @author Sergio Villanueva <sergiovillanueva@protonmail.com>
 */
@SpringBootTest
public class MemberData {
    public static final long INITIAL_ID = 1L;
    public static final String INITIAL_NAME = "Alkemy";
    public static final String INITIAL_DESCRIPTION = "Member";
    public static final String INITIAL_IMAGE = "alkemy.png";
    public static final LocalDateTime INITIAL_CREATED_AT = LocalDateTime.of(2021, 1, 1, 1, 1, 1);
    public static final LocalDateTime INITIAL_MODIFIED_AT = LocalDateTime.of(2021, 1, 1, 1, 1, 1);

    public static final String UPDATED_NAME = "Different Member";
    public static final String UPDATED_DESCRIPTION = "Member 2";
    public static final String UPDATED_IMAGE = "2nd-member.png";
    public static final LocalDateTime UPDATED_MODIFIED_AT = LocalDateTime.of(3000, 12, 12, 12, 12, 12);

    public static final int INITIAL_TOTALPAGES = 1;

    public static final long NONEXISTENT_ID = 402859203L;

    public static final String ROLE_USER = "ROLE_USER";

    public static MemberRequestDTO initialMemberRequestDTO() {
        MemberRequestDTO memberRequestDTO = new MemberRequestDTO();
        memberRequestDTO.setDescription(INITIAL_DESCRIPTION);
        memberRequestDTO.setImage(INITIAL_IMAGE);
        memberRequestDTO.setName(INITIAL_NAME);
        return memberRequestDTO;
    }

    public static MemberResponseDTO initialMemberResponseDTO() {
        MemberResponseDTO memberResponseDTO = new MemberResponseDTO();
        memberResponseDTO.setDescription(INITIAL_DESCRIPTION);
        memberResponseDTO.setImage(INITIAL_IMAGE);
        memberResponseDTO.setName(INITIAL_NAME);
        return memberResponseDTO;
    }

    public static PageDTO<MemberResponseDTO> initialPageDTO() {
        MemberResponseDTO memberResponseDTO = initialMemberResponseDTO();
        PageDTO<MemberResponseDTO> pageDTO = new PageDTO<>();
        pageDTO.setContent(Collections.singletonList(memberResponseDTO));
        pageDTO.setTotalPages(INITIAL_TOTALPAGES);
        return pageDTO;
    }

    public static MemberRequestDTO toUpdateMemberRequestDTO() {
        MemberRequestDTO memberRequestDTO = new MemberRequestDTO();
        memberRequestDTO.setDescription(INITIAL_DESCRIPTION);
        memberRequestDTO.setImage(INITIAL_IMAGE);
        memberRequestDTO.setName(INITIAL_NAME);
        return memberRequestDTO;
    }

    public static MemberResponseDTO updatedMemberRequestDTO() {
        MemberResponseDTO memberResponseDTO = new MemberResponseDTO();
        memberResponseDTO.setDescription(UPDATED_DESCRIPTION);
        memberResponseDTO.setImage(UPDATED_IMAGE);
        memberResponseDTO.setName(UPDATED_NAME);
        return memberResponseDTO;
    }

}
