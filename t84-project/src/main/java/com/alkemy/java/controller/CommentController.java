package com.alkemy.java.controller;

import com.alkemy.java.dto.CommentAllFieldsDTO;
import com.alkemy.java.dto.CommentDTO;
import com.alkemy.java.dto.CommentResponseDTO;
import com.alkemy.java.model.Comment;
import com.alkemy.java.service.CommentService;
import com.alkemy.java.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comments")
@Slf4j
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        log.debug("Request to get all Comments");
        return new ResponseEntity<>(commentService.findAll(), HttpStatus.OK );
    }
    @PostMapping
    public ResponseEntity<CommentResponseDTO>postComment(@Valid @RequestBody CommentAllFieldsDTO commentAllFieldsDTO){
        return new ResponseEntity<> (commentService.postComment(commentAllFieldsDTO),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentAllFieldsDTO> updateSlide(@RequestBody @Valid CommentDTO commentDTO, @PathVariable Long id){
        return new ResponseEntity<>(commentService.updateById(id,commentDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") Long id){
        commentService.deleteComment(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

