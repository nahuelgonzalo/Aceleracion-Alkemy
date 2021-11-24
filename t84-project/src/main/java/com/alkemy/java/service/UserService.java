package com.alkemy.java.service;

import java.util.List;

import com.alkemy.java.dto.*;
import com.alkemy.java.dto.IDTOQuery.IUserDTO;
import org.springframework.security.authentication.BadCredentialsException;
import com.alkemy.java.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public interface UserService {
    UserTokenDTO registrar(UserDTO usuarioDto);
    LoginResponseDTO login(LoginRequestDTO loginRequest) throws BadCredentialsException;
    List<UserResponseDTO> findAll();
    UserAuthDTO getUserLogged();
    boolean deleteUserById(Long userId);
    List<SimpleGrantedAuthority> getAuthoritiesUserLogged();
    UserDTO updateUser(UpdateUserDTO userDTO, Long id);
}
