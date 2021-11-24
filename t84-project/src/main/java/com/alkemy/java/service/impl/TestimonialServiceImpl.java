package com.alkemy.java.service.impl;

import com.alkemy.java.dto.PageDTO;
import com.alkemy.java.dto.TestimonialDTO;
import com.alkemy.java.exceptions.BadRequestException;
import com.alkemy.java.exceptions.NotFoundException;
import com.alkemy.java.mapper.TestimonialMapper;
import com.alkemy.java.model.Testimonial;
import com.alkemy.java.repository.TestimonialRepository;
import com.alkemy.java.service.AWSS3Service;
import com.alkemy.java.service.TestimonialService;
import com.alkemy.java.util.MessageUtil;
import com.alkemy.java.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

import static java.util.Locale.getDefault;

@Service
public class TestimonialServiceImpl implements TestimonialService {

    @Autowired
    private MessageUtil messageUtil;
    @Autowired
    private TestimonialMapper testimonialMapper;
    @Autowired
    private TestimonialRepository testimonialRepository;
    @Autowired
    private PageUtil pageUtil;
    @Autowired
    private AWSS3Service awss3Service;

    @Override
    public Testimonial saveTestimonial(TestimonialDTO testimonialDTO) {
        Testimonial testimonial = testimonialMapper.toTestimonial(testimonialDTO);
        testimonial.setImage(awss3Service.setImageBase64(testimonialDTO.getImage()));
        return testimonialRepository.save(testimonial);
    }

    @Override
    public PageDTO<TestimonialDTO> findAll(Integer page) {
        if (page <= 0)
            throw new IllegalArgumentException(messageUtil.getMessage("exception.pageDTO",null, getDefault()));
        Page<Testimonial> testimonialPage = testimonialRepository.findAllByDeletedFalse(pageUtil.pageRequest(page));
        if (page > testimonialPage.getTotalPages())
            throw new IllegalArgumentException(messageUtil.getMessage("exception.pageDTO.getTotal", new Object[]{testimonialPage.getTotalPages()},getDefault()));
        List<TestimonialDTO> testimonialDTOList = testimonialPage.getContent().stream()
                .map(testimonialMapper::toDto)
                .collect(Collectors.toList());
        PageDTO<TestimonialDTO> pageDTO = new PageDTO<>();
        pageDTO.setTotalPages(testimonialPage.getTotalPages());
        pageDTO.setContent(testimonialDTOList);
        if (testimonialPage.hasPrevious())
            pageDTO.setPreviousUrl(pageUtil.getPreviousUrl(page));
        if (testimonialPage.hasNext())
            pageDTO.setNextUrl(pageUtil.getNextUrl(page));
        return pageDTO;
    }

    @Override
    public String deleteTestimonialById(Long id) {
        Testimonial testimonial = testimonialRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Testimonial with id " + id + " not found"));
        if (testimonial.isDeleted())
            throw new BadRequestException("Testimonial with id " + id + " already deleted");
        testimonial.setDeleted(Boolean.TRUE);
        testimonialRepository.save(testimonial);
        return "Testimonial deleted succesfully";
    }

    @Override
    public TestimonialDTO updateTestimonial(TestimonialDTO testimonialUpdate, Long id) {
        Testimonial testimonial = testimonialRepository.findById(id).orElseThrow(()-> new NotFoundException(messageUtil.getMessage("exception.notFound.testimonial",
                new Long[]{id}, getDefault())));
        testimonial.setImage(awss3Service.setImageBase64(testimonialUpdate.getImage()));
        testimonial.setContent(testimonialUpdate.getContent());
        testimonial.setName(testimonialUpdate.getName());
        testimonial.setUpdatedAt(new Date());
        return testimonialMapper.toDto(testimonialRepository.save(testimonial));
    }
}
