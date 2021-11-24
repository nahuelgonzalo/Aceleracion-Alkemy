package com.alkemy.java.controller;

import com.alkemy.java.dto.ContactDTO;
import com.alkemy.java.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactDTO> saveContact(@Valid @RequestBody ContactDTO contactDTO){
        return new ResponseEntity<>(contactService.save(contactDTO), HttpStatus.OK );
    }

    @GetMapping
    public ResponseEntity<List<ContactDTO>> findAllContacts(){
        return new ResponseEntity<>(contactService.findAll(), HttpStatus.OK);
    }
}
