package com.alkemy.java.controller;

import com.alkemy.java.dto.*;
import com.alkemy.java.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@Api(tags = {"Category"})
@Slf4j
public class CategoryController {

    private static final String ADMIN_OPERATION_NOTE = "Accessible only by ADMIN";

    private static final String ALL_USERS_OPERATION_NOTE = "Accessible for all users";

    @Autowired
    CategoryService categoryService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Find category by ID", notes = ADMIN_OPERATION_NOTE)
    public ResponseEntity<CategoryDetailDTO> getCategoryById(
            @ApiParam("ID of the category to return") @PathVariable Long id
    ) {
        log.debug("Request to get Category with id: {}", id);
        return new ResponseEntity<>(categoryService.findDTOById(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Add a new category", notes = ADMIN_OPERATION_NOTE)
    public ResponseEntity<CategoryDTO> addCategory(
            @ApiParam("Category that needs to be added") @RequestBody CategoryRequestDTO categoryRequestDTO
    ) {
        return new ResponseEntity<>(categoryService.addCategory(categoryRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation(value = "Return paginated categories", notes = ALL_USERS_OPERATION_NOTE)
    public ResponseEntity<PageDTO<CategoryDTO>> findAllPageable(
            @ApiParam("Page number to return") @RequestParam(defaultValue = "1") Integer page
    ) {
        return new ResponseEntity<>(categoryService.findAll(page), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing category", notes = ADMIN_OPERATION_NOTE)
    public ResponseEntity<CategoryDTO> updateCategory(
            @ApiParam("Updated category object") @RequestBody CategoryRequestDTO categoryRequestDTO,
            @ApiParam("ID of the category to update") @PathVariable Long id
    ) {
        return new ResponseEntity<>(categoryService.updateCategory(categoryRequestDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a category", notes = ADMIN_OPERATION_NOTE)
    public ResponseEntity<Void> deleteCategory(
            @ApiParam("ID of the category to delete") @PathVariable Long id
    ) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
