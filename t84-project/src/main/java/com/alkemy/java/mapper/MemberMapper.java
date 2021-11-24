package com.alkemy.java.mapper;

import com.alkemy.java.dto.MemberRequestDTO;
import com.alkemy.java.dto.MemberResponseDTO;
import com.alkemy.java.model.Member;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    MemberResponseDTO memberToMemberResponseDTO(Member member);
    MemberRequestDTO memberToMemberRequestDTO(Member member);
    Member toMember(MemberRequestDTO memberRequestDTO);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(MemberRequestDTO memberRequestDTO, @MappingTarget Member member);

}
