package com.alkemy.java.repository;

import com.alkemy.java.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByOrderByCreatedAtDesc();
    @Query(value = "SELECT * FROM Comment c WHERE c.news_id=:id",nativeQuery = true)
    List<Comment> findCommentsByNewsId(@Param("id") Long id);
}
