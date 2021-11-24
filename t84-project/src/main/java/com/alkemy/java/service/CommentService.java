package com.alkemy.java.service;

import com.alkemy.java.dto.CommentAllFieldsDTO;
import com.alkemy.java.dto.CommentDTO;
import com.alkemy.java.dto.CommentResponseDTO;
import com.alkemy.java.model.Comment;

import java.util.List;

public interface CommentService {
    List<CommentDTO> findAll();
    CommentResponseDTO postComment(CommentAllFieldsDTO commentAllFieldsDTO);
    void deleteComment(Long id);
    CommentAllFieldsDTO updateById(Long id, CommentDTO commentDTO);
}
