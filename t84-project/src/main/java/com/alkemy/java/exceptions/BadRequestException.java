package com.alkemy.java.exceptions;

public class BadRequestException extends RuntimeException{

    private static final String description = "Bad Request Exception (400)";

   public BadRequestException(String detail){
         super(BadRequestException.description + " "+ detail);
    }
}
