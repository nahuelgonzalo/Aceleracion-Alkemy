package com.alkemy.java.controller;

import com.alkemy.java.dto.OrganizationDTO;
import com.alkemy.java.dto.OrganizationDetailDTO;
import com.alkemy.java.dto.OrganizationRequestDTO;
import com.alkemy.java.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    OrganizationService oService;

    @GetMapping("/public")
    public ResponseEntity<List<OrganizationDTO>> showdto() {
        return ResponseEntity.ok(oService.getAll());
    }

    @GetMapping("/public/{id}")
    public ResponseEntity<OrganizationDetailDTO> showOrganization(@PathVariable Long id) {
        return new ResponseEntity<>(oService.findOrganizationDetail(id), HttpStatus.OK);
    }

    @PutMapping("/public/{id}")
    public ResponseEntity<OrganizationDTO> updateOrganization(@RequestBody @Valid OrganizationRequestDTO organizationRequestDTO, @PathVariable Long id){
        return new ResponseEntity<>(oService.update(organizationRequestDTO,id),HttpStatus.OK);
    }
}