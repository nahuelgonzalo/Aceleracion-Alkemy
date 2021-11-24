package com.alkemy.java.service;

import com.alkemy.java.dto.MemberRequestDTO;
import com.alkemy.java.dto.MemberResponseDTO;
import com.alkemy.java.dto.PageDTO;

public interface MemberService {
    PageDTO<MemberResponseDTO> findAll(Integer page);
    MemberResponseDTO save(MemberRequestDTO memberRequestDTO);
    void deleteById(Long id);
    MemberResponseDTO updateMember(MemberRequestDTO memberRequestDTO, Long id);
}
