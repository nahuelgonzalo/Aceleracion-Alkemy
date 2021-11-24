package com.alkemy.java.service.impl;

import com.alkemy.java.dto.SlideRequestDTO;
import com.alkemy.java.dto.SlideResponseDTO;
import com.alkemy.java.exceptions.NotFoundException;
import com.alkemy.java.exceptions.UploadImageException;
import com.alkemy.java.mapper.SlideMapper;
import com.alkemy.java.model.Slide;
import com.alkemy.java.model.User;
import com.alkemy.java.repository.SlideRepository;
import com.alkemy.java.repository.UserRepository;
import com.alkemy.java.service.AWSS3Service;
import com.alkemy.java.service.BASE64DecodingAndEncodingService;
import com.alkemy.java.service.SlideService;
import com.alkemy.java.service.UserService;
import com.alkemy.java.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import static java.util.Locale.getDefault;
import com.alkemy.java.exceptions.NoAuthorizeException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SlideServiceImpl implements SlideService {

    @Autowired
    SlideMapper slideMapper;

    @Autowired
    SlideRepository slideRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageUtil messageUtil;

    @Autowired
    AWSS3Service awss3Service;

    @Autowired
    BASE64DecodingAndEncodingService base64DecodingAndEncodingService;

    @Autowired
    UserService userService;

    @Override
    public SlideRequestDTO addSlide(SlideRequestDTO slideRequestDto){
        Slide slide = slideMapper.toSlide(slideRequestDto);
        if(slideRequestDto.getOrder()==null)setOrder(slide);
        slide.setImageUrl(awss3Service.setImageBase64(slideRequestDto.getImageUrl()));
        return slideMapper.slideToSlideRequestDto(slideRepository.save(slide));
    }

    public List<SlideResponseDTO> findAllWithJustImageAndOrder (){
        return slideRepository.findAll().stream()
                .map(slideMapper::toDTOJustImageAndOrder)
                .collect(Collectors.toList());
    }

    @Override
    public SlideResponseDTO findById(Long id) {
        Slide slide = slideRepository.findById(id).orElseThrow(() ->
             new NotFoundException(messageUtil.getMessage("exception.notFound",null, getDefault()))
        );
        return slideMapper.slideToSlideResponseDto(slide);
    }

    @Override
    @Transactional
    public void deleteSlide(Long id) {
        slideRepository.findById(id).orElseThrow(()-> new NotFoundException("Slide not found"));
        User user = userRepository.findByEmail(userService.getUserLogged().getEmail()).orElseThrow(()-> new NotFoundException("User not found"));
        if(user.getRole().getName() == "ROLE_USER"){
            slideRepository.deleteById(id);
        }else{
            throw new NoAuthorizeException("Not authorized");
        }
    }

    @Override
    @Transactional
    public SlideResponseDTO updateSlide(Long id, SlideRequestDTO slideRequestDto){
        if (!slideRepository.existsById(id))
            throw new NotFoundException(
                messageUtil.getMessage(
                        "exception.notFound",
                        null,
                        Locale.getDefault()
                ));
        Slide slide = slideMapper.toSlide(slideRequestDto);
        slide.setId(id);
        if(slide.getOrder()==null){
            setOrder(slide);
        }
        slide.setImageUrl(awss3Service.setImageBase64(slideRequestDto.getImageUrl()));
        return slideMapper.slideToSlideResponseDto(slideRepository.save(slide));
    }

    private void setOrder(Slide slide) {
        Integer lastOrderInSlides = slideRepository.findByOrganizationWithLastOrder(slide.getOrganization());
        slide.setOrder(lastOrderInSlides == null ? 1: lastOrderInSlides + 1);
    }
}
