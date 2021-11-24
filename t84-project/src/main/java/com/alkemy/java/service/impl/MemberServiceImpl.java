package com.alkemy.java.service.impl;

import com.alkemy.java.dto.MemberRequestDTO;
import com.alkemy.java.dto.MemberResponseDTO;
import com.alkemy.java.dto.PageDTO;
import com.alkemy.java.exceptions.BadRequestException;
import com.alkemy.java.exceptions.NotFoundException;
import com.alkemy.java.mapper.MemberMapper;
import com.alkemy.java.model.Member;
import com.alkemy.java.repository.MemberRepository;
import com.alkemy.java.service.AWSS3Service;
import com.alkemy.java.service.MemberService;
import com.alkemy.java.util.MessageUtil;
import com.alkemy.java.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.Locale.getDefault;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private PageUtil pageUtil;

    @Autowired
    private AWSS3Service awss3Service;

    @Override
    public PageDTO<MemberResponseDTO> findAll(Integer page) {
        if (page <= 0)
            throw new IllegalArgumentException(messageUtil.getMessage("exception.pageDTO",null, getDefault()));

        Page<Member> memberPage = memberRepository.findAllByDeletedFalse(pageUtil.pageRequest(page));

        if (memberPage.getTotalPages() != 0 && page > memberPage.getTotalPages())
            throw new IllegalArgumentException(messageUtil.getMessage("exception.pageDTO.getTotal", new Object[]{memberPage.getTotalPages()},getDefault()));

        List<MemberResponseDTO> memberResponseDTOList = memberPage.getContent().stream()
                .map(memberMapper::memberToMemberResponseDTO)
                .collect(Collectors.toList());
        PageDTO<MemberResponseDTO> pageDTO = new PageDTO<>();
        pageDTO.setTotalPages(memberPage.getTotalPages());
        pageDTO.setContent(memberResponseDTOList);

        if (memberPage.hasPrevious())
            pageDTO.setPreviousUrl(pageUtil.getPreviousUrl(page));
        if (memberPage.hasNext())
            pageDTO.setNextUrl(pageUtil.getNextUrl(page));

        return pageDTO;
    }

    @Override
    public MemberResponseDTO save(MemberRequestDTO memberRequestDTO) {
        if(memberRequestDTO.getName().isEmpty())
            throw new BadRequestException("Member name cant be empty");

        Member member = memberMapper.toMember(memberRequestDTO);
        member.setImage(awss3Service.setImageBase64(memberRequestDTO.getImage()));
        return memberMapper.memberToMemberResponseDTO(memberRepository.save(member));
    }

    @Override
    public void deleteById(Long id) {
        if(!memberRepository.existsByIdAndDeletedFalse(id))
            throw new NotFoundException(messageUtil.getMessage("exception.notFound",null, getDefault()));
        memberRepository.deleteById(id);
    }

    @Override
    public MemberResponseDTO updateMember(MemberRequestDTO memberRequestDTO, Long id) {
        return memberRepository
                .findByIdAndDeletedFalse(id)
                .map(m -> {
                            memberMapper.updateFromDTO(memberRequestDTO, m);
                            m.setImage(awss3Service.setImageBase64(memberRequestDTO.getImage()));
                            m = memberRepository.save(m);
                            return memberMapper.memberToMemberResponseDTO(m);
                        }
                ).orElseThrow(() -> new NotFoundException(messageUtil.getMessage("exception.notFound.member",
                        new Long[]{id}, getDefault())));
    }

}
