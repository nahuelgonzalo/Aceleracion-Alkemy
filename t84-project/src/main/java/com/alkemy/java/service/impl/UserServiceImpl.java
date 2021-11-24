package com.alkemy.java.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.alkemy.java.dto.*;
import com.alkemy.java.dto.IDTOQuery.IUserDTO;
import com.alkemy.java.exceptions.BadRequestException;
import com.alkemy.java.exceptions.ForbiddenException;
import com.alkemy.java.exceptions.NotFoundException;
import com.alkemy.java.mapper.UserMapper;
import com.alkemy.java.repository.RoleRepository;
import com.alkemy.java.service.AWSS3Service;
import com.alkemy.java.service.EmailService;
import com.alkemy.java.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alkemy.java.model.User;
import com.alkemy.java.repository.UserRepository;
import com.alkemy.java.service.UserService;
import com.alkemy.java.util.JwtUtil;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EmailService emailService;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private AWSS3Service awss3Service;

    @Override
    public UserTokenDTO registrar(UserDTO usuarioDto) {
        User user = User.builder()
                .firstName(usuarioDto.getName())
                .lastName(usuarioDto.getSurname())
                .email(usuarioDto.getEmail())
                .photo(awss3Service.setImageBase64(usuarioDto.getPhoto()))
                .role(roleRepository.findByName("ROLE_USER"))
                .password(passwordEncoder.encode(usuarioDto.getPassword()))
                .build();
        //Validation that the email does not exist in the DB (The @Where annotation in the entity, filters only non-deleted users).
        if (userRepository.existsByEmail(usuarioDto.getEmail())){
            throw new BadRequestException(messageUtil.getMessage(
                    "exception.badRequest.user.emailExist",
                    null,
                    Locale.getDefault()));
        }
        userRepository.save(user);
        emailService.sendTemplateBienvenidaHTML(usuarioDto);
        UserTokenDTO userToken = new UserTokenDTO();
        String token = login(new LoginRequestDTO(usuarioDto.getEmail(),usuarioDto.getPassword())).getAccessToken();
        userToken.setUserResponseDTO(userMapper.toUserResponseDTO(user));
        userToken.setToken(token);
        return userToken;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequest) throws BadCredentialsException {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtUtil.generateToken(userDetails);
            LoginResponseDTO loginResponse = new LoginResponseDTO();
            loginResponse.setAccessToken(jwt);
            return loginResponse;
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getLocalizedMessage());
        }
    }
    
    @Override
    public List<UserResponseDTO> findAll(){
        List<IUserDTO> iUserDTOS = userRepository.getUsers();
        return iUserDTOS.stream().map(userMapper::toUserResponseDTO).collect(Collectors.toList());
    }

    public UserAuthDTO getUserLogged() {
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        Optional<User> userOptional = userRepository.findByEmail(userDetail.getUsername());
        UserAuthDTO userAuthDTO = userMapper.toUserAuthDTO(userOptional.get());
        return userAuthDTO;
    }

    @Transactional
    public boolean deleteUserById(Long userId) {
        //Validation that the user logged can only delete himself
        if (!Objects.equals(getUserLogged().getId(), userId)) return false;
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new NotFoundException(
                                messageUtil.getMessage(
                                        "exception.notFound.user",
                                        null,
                                        Locale.getDefault()
                                )));
        user.setDeleted(Boolean.TRUE);
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDTO updateUser(UpdateUserDTO updateUserDTO, Long id) {
        if(!getUserLogged().getId().equals(id) && !getUserLogged().getRole().equals(roleRepository.findByName("ROLE_ADMIN")))
            throw new ForbiddenException((messageUtil.getMessage(
                    "exception.forbidden",
                    null,
                    Locale.getDefault())));
        User user = userRepository.findById(id)
                .orElseThrow(()->new NotFoundException(messageUtil.getMessage(
                        "exception.notFound.user",
                        null,
                        Locale.getDefault())));
        user.setFirstName(updateUserDTO.getFirstName());
        user.setLastName(updateUserDTO.getLastName());
        user.setPhoto(awss3Service.setImageBase64(updateUserDTO.getPhoto()));
        userRepository.save(user);
        return userMapper.toUserDTO(userRepository.save(user));
    }


    @Override
    public List<SimpleGrantedAuthority> getAuthoritiesUserLogged() {
        return (List<SimpleGrantedAuthority>) SecurityContextHolder
                .getContext().getAuthentication().getAuthorities();
    }
}
