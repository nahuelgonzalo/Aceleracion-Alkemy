package com.alkemy.java.controller;

import com.alkemy.java.dto.CommentResponseDTO;
import com.alkemy.java.dto.NewRequestDTO;
import com.alkemy.java.dto.NewResponseDTO;
import com.alkemy.java.dto.PageDTO;
import com.alkemy.java.service.NewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/news")
@Api(tags = {"New Controller"})
public class NewController {

    @Autowired
    private NewService newService;

    @GetMapping()
    @ApiOperation(
            value = "Get paginated News",
            notes = "The endpoint is used to get a pagination with a list of 10 News, the total of pages, the previous and next url. " +
                    "In the case that there is no previous and/or next, it returns the field with a null value.")
    public ResponseEntity<PageDTO<NewResponseDTO>> getAll(
            @RequestParam(name = "page", defaultValue = "1") @ApiParam("Page number") Integer page){
        return new ResponseEntity<>(newService.findAll(page), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(
            value = "Get New by id",
            notes = "The endpoint is used to get a New by id.")
    public ResponseEntity<NewResponseDTO> getNewById(
            @ApiParam(value = "idNews", defaultValue = "1") @PathVariable Long id){
        return new ResponseEntity<>(newService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(
            value = "Create and save a New",
            notes = "The endpoint is used to create a New and is saved in the database")
    public ResponseEntity<NewResponseDTO> saveNew(@Valid @RequestBody @ApiParam(name = "New", value = "new") NewRequestDTO newsDTO) {
        return new ResponseEntity<>(newService.save(newsDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/comments")
    @ApiOperation(
            value = "Get comments of a New by id",
            notes = "The endpoint get a list comments of a New, sending its id like param")
    public ResponseEntity<List<CommentResponseDTO>> getListComments(@PathVariable Long id) {
        return new ResponseEntity<>(newService.listComment(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Delete New by id",
            notes = "The endpoint executes a Soft-Delete on a New")
    public ResponseEntity<String> deleteNewsById(@PathVariable Long id) {
        newService.deleteNewsById(id);
        return new ResponseEntity<>("News deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation(
            value = "Update New by id",
            notes = "The endpoint updates the fields of a New by id")
    public ResponseEntity<NewResponseDTO> updateNew(
            @Valid @RequestBody @ApiParam("new") NewRequestDTO newDTO,
            @PathVariable @ApiParam(value = "idNews", defaultValue = "1") Long id) {
        return new ResponseEntity<>(newService.updateNew(newDTO, id), HttpStatus.OK);
    }
}
