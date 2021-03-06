package com.alkemy.java.repository;

import com.alkemy.java.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Optional<Organization> findByIdAndDeletedFalse(Long id);
}
