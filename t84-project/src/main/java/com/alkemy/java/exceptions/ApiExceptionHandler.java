package com.alkemy.java.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private Map<String,Object> response = new HashMap<>();

    @ExceptionHandler({BadRequestException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorMessage> badRequestErrorResponse(Exception e, HttpServletRequest req){
        return new ResponseEntity<>(new ErrorMessage(e,req.getRequestURI()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoAuthorizeException.class)
    public ResponseEntity<ErrorMessage> unauthorizedErrorResponse(Exception ex, HttpServletRequest req){
        return new ResponseEntity<>(new ErrorMessage(ex,req.getRequestURI()),HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundErrorResponse(NotFoundException ex){
        response.clear();
        response.put("message", ex.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<?> invalidFormat(NumberFormatException ex){
        response.clear();
        response.put("message", ex.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> dataIntegrityViolation(Exception e, HttpServletRequest req){
        return new ResponseEntity<>(new ErrorMessage(e,req.getRequestURI()),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UploadImageException.class)
    public ResponseEntity<ErrorMessage> uploadImageErrorResponse(Exception e, HttpServletRequest req){
        return new ResponseEntity<>(new ErrorMessage(e,req.getRequestURI()),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BASE64ImageFormatException.class)
    public ResponseEntity<ErrorMessage> base64ImageFormatErrorResponse(Exception e, HttpServletRequest req){
        return new ResponseEntity<>(new ErrorMessage(e,req.getRequestURI()),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorMessage> forbiddenResponse(Exception e, HttpServletRequest req){
        return new ResponseEntity<>(new ErrorMessage(e,req.getRequestURI()),HttpStatus.FORBIDDEN);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        response.clear();
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField().concat(" ").concat( err.getDefaultMessage()))
                .collect(Collectors.toList());
        response.put("timestamp",LocalDateTime.now());
        response.put("errors",errors);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
