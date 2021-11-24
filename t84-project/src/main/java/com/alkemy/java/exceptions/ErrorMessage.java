package com.alkemy.java.exceptions;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;


@Data
public class ErrorMessage {
    
    private String path;
    private String message;
    private String exception;
    private LocalDateTime timestamp;
    
    public ErrorMessage(Exception exception,String path){
        this.exception = exception.getClass().getSimpleName();
        this.message =  exception.getMessage();
        this.path =  path;
        this.timestamp = LocalDateTime.now();
    }
    
}
