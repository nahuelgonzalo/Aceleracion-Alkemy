package com.alkemy.java.controller;

import com.alkemy.java.dto.PageDTO;
import com.alkemy.java.dto.TestimonialDTO;
import com.alkemy.java.model.Testimonial;
import com.alkemy.java.service.TestimonialService;
import com.alkemy.java.util.MessageUtil;
import com.alkemy.java.util.PageUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/testimonials")
public class TestimonialController {

    @Autowired
    private TestimonialService testimonialService;

    @Autowired
    private PageUtil pageUtil;

    @Autowired
    private MessageUtil messageUtil;

    @PostMapping
    @ApiOperation(value="create new Testimonial" , notes = "Required role admin")
    public ResponseEntity<TestimonialDTO> saveTestimonial(@Valid @RequestBody TestimonialDTO testimonialDTO) {
        testimonialService.saveTestimonial(testimonialDTO);
        return new ResponseEntity<>(testimonialDTO, HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation(value="Get all Testimonial in pages" , notes = "Required role admin")
    public PageDTO<TestimonialDTO> getAllTestimonials(@RequestParam(defaultValue = "1") Integer page) {
        return testimonialService.findAll(page);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value="Delete testimonial by Id" , notes = "Required role admin")
    public ResponseEntity<String> deleteTestimonial(@ApiParam(value = "Example enter: 1 by delete testimonial")
                                               @PathVariable("id") Long id) {
        return new ResponseEntity<>(testimonialService.deleteTestimonialById(id), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    @ApiOperation(value="Update testimonial by Id" , notes = "Required role admin")
    public ResponseEntity<TestimonialDTO> updateTestimonial(@Valid @RequestBody TestimonialDTO testimonial,
                                                            @ApiParam(value = "Example enter: 1 by update testimonial")
                                                            @PathVariable("id") Long id){
        return new ResponseEntity<>(testimonialService.updateTestimonial(testimonial,id), HttpStatus.OK);
    }
}
