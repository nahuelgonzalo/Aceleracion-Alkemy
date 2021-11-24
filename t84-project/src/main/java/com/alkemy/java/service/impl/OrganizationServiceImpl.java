package com.alkemy.java.service.impl;

import com.alkemy.java.dto.OrganizationDTO;
import com.alkemy.java.dto.OrganizationDetailDTO;
import com.alkemy.java.dto.OrganizationRequestDTO;
import com.alkemy.java.exceptions.BadRequestException;
import com.alkemy.java.exceptions.NotFoundException;
import com.alkemy.java.mapper.OrganizationMapper;
import com.alkemy.java.mapper.SlideMapper;
import com.alkemy.java.model.Organization;
import com.alkemy.java.model.Slide;
import com.alkemy.java.repository.OrganizationRepository;
import com.alkemy.java.repository.SlideRepository;
import com.alkemy.java.service.AWSS3Service;
import com.alkemy.java.service.OrganizationService;
import com.alkemy.java.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private SlideRepository slideRepository;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private SlideMapper slideMapper;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private AWSS3Service awss3Service;

    @Override
    public Organization findById(Long id) {
        return organizationRepository.findById(id).orElseThrow(()->
                new BadRequestException(
                        messageUtil.getMessage(
                                "exception.notFound.organization",
                                new Long[]{id},
                                Locale.getDefault())));
    }

    @Override
    public List<OrganizationDTO> getAll() {
        return organizationRepository.findAll().stream()
                .map(organization -> organizationMapper.toDto(organization))
                .collect(Collectors.toList());
    }

    @Override
    public OrganizationDetailDTO findOrganizationDetail(Long id) {
        OrganizationDetailDTO organizationDetailDTO = organizationRepository.findByIdAndDeletedFalse(id)
                    .map(o -> organizationMapper.toDetailDto(o))
                    .orElseThrow(() -> new NotFoundException(messageUtil.getMessage("exception.notFound", null, Locale.getDefault())));

        List<Slide> slides = slideRepository.findByOrganizationIdOrderByOrderAsc(id);
        organizationDetailDTO.setSlides(slideMapper.toSlideImageDTO(slides));

        return organizationDetailDTO;
    }

    @Override
    public OrganizationDTO update(OrganizationRequestDTO organizationRequestDTO, Long id) {
        Organization organization = organizationRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(()->new BadRequestException(messageUtil.getMessage("exception.notFound", null, Locale.getDefault())));
        organization.setName(organizationRequestDTO.getName());
        organization.setImage(awss3Service.setImageBase64(organizationRequestDTO.getImage()));
        organization.setPhone(organizationRequestDTO.getPhone());
        organization.setAddress(organizationRequestDTO.getAddress());
        organization.setEmail(organizationRequestDTO.getEmail());
        organization.setWelcomeText(organizationRequestDTO.getWelcomeText());
        organization.setAboutUsText(organizationRequestDTO.getAboutUsText());
        organization.setFacebookUrl(organizationRequestDTO.getFacebookUrl());
        organization.setLinkedinUrl(organizationRequestDTO.getLinkedinUrl());
        organization.setInstagramUrl(organizationRequestDTO.getInstagramUrl());
        organization.setId(id);
      return organizationMapper.toDto(organizationRepository.save(organization));
    }
}