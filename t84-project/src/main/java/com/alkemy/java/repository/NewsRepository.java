package com.alkemy.java.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alkemy.java.model.News;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>{
    Page<News> findAllByDeletedFalse(Pageable pageable);
    @Query(value = "select * from News n where n.id =:id and n.deleted =false",nativeQuery = true)
    public Optional<News> findById(@Param("id") Long id);
    @Modifying
    @Query(value = "UPDATE News n SET n.deleted = true, n.delete_at = NOW() WHERE n.id =:id",nativeQuery = true)
    public void deleteNewsById(@Param("id") Long id);
}
