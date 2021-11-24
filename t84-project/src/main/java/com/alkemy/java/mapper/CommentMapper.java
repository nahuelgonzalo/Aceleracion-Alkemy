package com.alkemy.java.mapper;

import com.alkemy.java.dto.CommentAllFieldsDTO;
import com.alkemy.java.dto.CommentDTO;
import com.alkemy.java.dto.CommentResponseDTO;
import com.alkemy.java.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "newsId", source = "comment.news.id")
    CommentAllFieldsDTO toAllFieldsDTO(Comment comment);
    CommentDTO toDto(Comment comment);
    Comment toComment(CommentAllFieldsDTO commentAllFieldsDTO);
    List<CommentResponseDTO> listToDto(List<Comment>list);
    CommentResponseDTO toCommentResponse (Comment comment);
}