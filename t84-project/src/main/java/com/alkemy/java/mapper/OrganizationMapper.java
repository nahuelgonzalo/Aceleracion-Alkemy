package com.alkemy.java.mapper;

import com.alkemy.java.dto.OrganizationDTO;
import com.alkemy.java.dto.OrganizationDetailDTO;
import com.alkemy.java.dto.OrganizationRequestDTO;
import com.alkemy.java.model.Organization;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {
     OrganizationDetailDTO toDetailDto(Organization organization);
     OrganizationDTO toDto(Organization o);
     Organization fromRequestToOrganization(OrganizationRequestDTO organizationRequestDTO);
}
