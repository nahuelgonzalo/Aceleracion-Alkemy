package com.alkemy.java.repository;

import com.alkemy.java.model.Organization;
import com.alkemy.java.model.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlideRepository extends JpaRepository<Slide,Long> {
    List<Slide> findByOrganizationIdOrderByOrderAsc(Long organizationId);

    @Query("select max(s.order) from Slide s where s.organization=?1")
    Integer findByOrganizationWithLastOrder(Organization organization);
}