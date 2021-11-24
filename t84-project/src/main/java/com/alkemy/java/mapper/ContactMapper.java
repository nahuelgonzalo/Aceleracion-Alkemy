package com.alkemy.java.mapper;

import com.alkemy.java.dto.ContactDTO;
import com.alkemy.java.model.Contact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    ContactDTO toDTO (Contact contact);
    Contact toContact (ContactDTO contactDTO);
}