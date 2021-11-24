package com.alkemy.java.service;

import com.alkemy.java.dto.CommentResponseDTO;
import com.alkemy.java.dto.NewRequestDTO;
import com.alkemy.java.dto.NewResponseDTO;
import com.alkemy.java.dto.PageDTO;

import java.util.List;

public interface NewService {
    NewResponseDTO findById(Long id);
    NewResponseDTO save(NewRequestDTO newsDTO);
    List<CommentResponseDTO> listComment(Long id);
    NewResponseDTO updateNew(NewRequestDTO newDTO, Long id);
    PageDTO<NewResponseDTO> findAll(Integer page);
    void deleteNewsById(Long id);
}
