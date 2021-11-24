package com.alkemy.java.repository;

import com.alkemy.java.model.Category;
import com.alkemy.java.model.Member;
import com.alkemy.java.model.Testimonial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestimonialRepository extends JpaRepository<Testimonial,Long> {

    @Query("select c from Testimonial c where c.deleted=false")
    List<Testimonial> getAllWithDeletedFalse();

    Page<Testimonial> findAllByDeletedFalse(Pageable pageable);


}
