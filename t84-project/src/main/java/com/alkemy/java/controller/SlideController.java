package com.alkemy.java.controller;

import com.alkemy.java.dto.SlideRequestDTO;
import com.alkemy.java.dto.SlideResponseDTO;
import com.alkemy.java.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/slides")
public class SlideController {

    @Autowired
    SlideService slideService;

    @PostMapping
    public ResponseEntity<SlideRequestDTO> addSlide(@Valid @RequestBody SlideRequestDTO slideRequestDTO){
        try {
            return new ResponseEntity<>(slideService.addSlide(slideRequestDTO), HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(slideRequestDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<SlideResponseDTO>> findAllWithJustImageAndOrder (){
        List<SlideResponseDTO> response = slideService.findAllWithJustImageAndOrder();
        return new ResponseEntity<>(response, response.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlideResponseDTO> findById (@PathVariable Long id){
        SlideResponseDTO response = slideService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        slideService.deleteSlide(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SlideResponseDTO> updateSlide(@RequestBody @Valid SlideRequestDTO slideDTO, @PathVariable Long id){
        return new ResponseEntity<>(slideService.updateSlide(id,slideDTO),HttpStatus.OK);
    }
}
