package com.alkemy.java.service;

import com.alkemy.java.dto.OrganizationDTO;
import com.alkemy.java.dto.OrganizationDetailDTO;
import com.alkemy.java.dto.OrganizationRequestDTO;
import com.alkemy.java.model.Organization;

import java.util.List;

public interface OrganizationService {
    Organization findById(Long id);
    List<OrganizationDTO> getAll();
    OrganizationDetailDTO findOrganizationDetail(Long id);
    OrganizationDTO update(OrganizationRequestDTO organizationRequestDTO, Long id);
}
