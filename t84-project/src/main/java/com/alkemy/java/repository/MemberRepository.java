package com.alkemy.java.repository;

import com.alkemy.java.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Page<Member> findAllByDeletedFalse(Pageable pageable);
    boolean existsByIdAndDeletedFalse(Long id);
    Optional<Member> findByIdAndDeletedFalse(Long id);
}
