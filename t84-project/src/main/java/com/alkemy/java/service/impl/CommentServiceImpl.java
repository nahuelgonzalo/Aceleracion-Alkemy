package com.alkemy.java.service.impl;

import com.alkemy.java.dto.CommentAllFieldsDTO;
import com.alkemy.java.dto.CommentDTO;
import com.alkemy.java.dto.CommentResponseDTO;
import com.alkemy.java.exceptions.BadRequestException;
import com.alkemy.java.exceptions.NoAuthorizeException;
import com.alkemy.java.exceptions.ForbiddenException;
import com.alkemy.java.exceptions.NotFoundException;
import com.alkemy.java.mapper.CommentMapper;
import com.alkemy.java.model.Comment;
import com.alkemy.java.model.News;
import com.alkemy.java.model.User;
import com.alkemy.java.repository.CommentRepository;
import com.alkemy.java.repository.NewsRepository;
import com.alkemy.java.repository.UserRepository;
import com.alkemy.java.service.CommentService;
import com.alkemy.java.util.JwtUtil;
import com.alkemy.java.service.UserService;
import com.alkemy.java.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    private MessageUtil messageUtil;
    @Autowired
    private UserService userService;

    @Override
    public List<CommentDTO> findAll() {
        return commentRepository.findAllByOrderByCreatedAtDesc().
                stream().map(commentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CommentResponseDTO postComment(CommentAllFieldsDTO commentAllFieldsDTO) {
        Comment comment = new Comment();
        News news = newsRepository.findById(commentAllFieldsDTO.getNewsId()).orElseThrow(() -> new BadRequestException(
                messageUtil.getMessage("exception.notFound.new", new Object[]{commentAllFieldsDTO.getNewsId()}, Locale.getDefault())));
        User user = userRepository.findById(userService.getUserLogged().getId()).orElseThrow(() -> new BadRequestException
                (messageUtil.getMessage("exception.notFound.user", null, Locale.getDefault())));
        comment = commentMapper.toComment(commentAllFieldsDTO);
        comment.setCreatedAt(Calendar.getInstance().getTime());
        comment.setUpdatedAt(Calendar.getInstance().getTime());
        comment.setNews(news);
        comment.setUser(user);
        commentRepository.save(comment);
        return commentMapper.toCommentResponse(comment);
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment not found"));
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User userJwt = userRepository.findByEmail(username).orElseThrow(() -> new NotFoundException("Username not found"));
        if (!userJwt.equals(comment.getUser())&&!userJwt.getRole().getName().equals("ROLE_ADMIN"))
                throw new NoAuthorizeException("No authorization for this user");
        commentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CommentAllFieldsDTO updateById(Long id, CommentDTO commentDTO) {
        //The comment is searched by id, and it is validated that exists
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NotFoundException(
                messageUtil.getMessage(
                        "exception.notFound.comment",
                        new Long[]{id},
                        Locale.getDefault())));
        //Validation of allowed user
        boolean isAdmin = userService.getAuthoritiesUserLogged().stream()
                .anyMatch(p -> p.getAuthority().equals("ROLE_ADMIN"));
        boolean isSameUser = userService.getUserLogged().getId()
                .equals(comment.getUser().getId());
        if (!isSameUser && !isAdmin)
            throw new ForbiddenException(
                    messageUtil.getMessage(
                            "exception.forbidden",
                            null,
                            Locale.getDefault()));
        //Comment update
        comment.setBody(commentDTO.getBody());
        comment.setUpdatedAt(new Date());
        return commentMapper.toAllFieldsDTO(commentRepository.save(comment));
    }
}
