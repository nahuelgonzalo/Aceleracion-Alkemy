package com.alkemy.java.service;

import com.alkemy.java.dto.ContactDTO;

import java.util.List;

public interface ContactService {
    void sendEmail(String to);
    ContactDTO save(ContactDTO contactDTO);
    List<ContactDTO> findAll();
}
