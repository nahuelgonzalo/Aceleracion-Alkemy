package com.alkemy.java.service.impl;

import com.alkemy.java.dto.ContactDTO;
import com.alkemy.java.mapper.ContactMapper;
import com.alkemy.java.repository.ContactRepository;
import com.alkemy.java.service.ContactService;
import com.alkemy.java.service.EmailService;
import com.alkemy.java.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    ContactMapper contactMapper;

    @Autowired
    EmailService emailService;

    @Autowired
    MessageUtil messageUtil;

    @Override
    @Transactional
    public void sendEmail(String to) {
        emailService.sendText(to,
                messageUtil.getMessage("contact.register.subject", null, Locale.getDefault()),
                messageUtil.getMessage("contact.register.body", null, Locale.getDefault()),
                null);
    }

    @Override
    public ContactDTO save(ContactDTO contactDTO) {
        sendEmail(contactDTO.getEmail());
        return contactMapper.toDTO(contactRepository.save(contactMapper.toContact(contactDTO)));
    }

    @Override
    public List<ContactDTO> findAll() {
        return contactRepository.findAll().stream()
                                .map(contactMapper::toDTO)
                                .collect(Collectors.toList());
    }


}
