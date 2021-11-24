package com.alkemy.java.repository;

import com.alkemy.java.dto.ActivityDTO;
import com.alkemy.java.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Optional<Activity> findByIdAndDeletedFalse(Long id);
}
