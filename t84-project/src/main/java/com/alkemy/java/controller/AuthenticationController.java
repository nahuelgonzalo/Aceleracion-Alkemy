package com.alkemy.java.controller;

import com.alkemy.java.dto.LoginRequestDTO;
import com.alkemy.java.dto.LoginResponseDTO;
import com.alkemy.java.dto.UserDTO;
import com.alkemy.java.dto.UserTokenDTO;
import com.alkemy.java.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/auth")
@Api(tags = {"Authentication"})
@Slf4j
public class AuthenticationController {

    private static final String ALL_USERS_OPERATION_NOTE = "Accessible for all users";

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Register", notes = ALL_USERS_OPERATION_NOTE)
    @PostMapping("/register")
    public ResponseEntity<UserTokenDTO> createAccount(@ApiParam("User to be registered")@Valid @RequestBody UserDTO userDto){
        UserTokenDTO user = userService.registrar(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Login", notes = ALL_USERS_OPERATION_NOTE)
    @PostMapping("/login")
    public ResponseEntity<?> login(@ApiParam("User to login")@Valid @RequestBody LoginRequestDTO loginRequest) {
        try {
            log.debug("Request to authenticate user {}", loginRequest.getEmail());
            LoginResponseDTO loginResponse = userService.login(loginRequest);
            return ResponseEntity.ok(loginResponse);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Collections.singletonMap("message", "Username or password is incorrect"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @ApiOperation(value = "Returns the logged in user", notes = ALL_USERS_OPERATION_NOTE)
    @GetMapping("/me")
    public ResponseEntity<?> getUserLogged(){
        return new ResponseEntity<>(userService.getUserLogged(), HttpStatus.OK);
    }
}
