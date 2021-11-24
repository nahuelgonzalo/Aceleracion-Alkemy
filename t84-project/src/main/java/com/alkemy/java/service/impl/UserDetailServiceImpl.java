package com.alkemy.java.service.impl;

import com.alkemy.java.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(u -> {
                    GrantedAuthority authority;
                    if (u.getRole() == null)
                        authority = new SimpleGrantedAuthority("ROLE_USER");
                    else
                        authority = new SimpleGrantedAuthority(u.getRole().getName());
                    return new User(u.getEmail(), u.getPassword(), List.of(authority));
                }).orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
