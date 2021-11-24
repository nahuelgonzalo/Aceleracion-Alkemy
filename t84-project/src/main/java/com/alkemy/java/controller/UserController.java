package com.alkemy.java.controller;

import java.util.List;

import com.alkemy.java.dto.UpdateUserDTO;
import com.alkemy.java.dto.UserDTO;
import com.alkemy.java.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alkemy.java.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteUsers(@PathVariable("id") Long userId) {
        return new ResponseEntity<>(userService.deleteUserById(userId) ? HttpStatus.OK : HttpStatus.FORBIDDEN);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UpdateUserDTO updateUserDTO, @PathVariable Long id){
        return new ResponseEntity<>(userService.updateUser(updateUserDTO,id), HttpStatus.OK);
    }
}