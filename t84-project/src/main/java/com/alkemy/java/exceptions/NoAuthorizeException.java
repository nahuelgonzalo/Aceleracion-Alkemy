package com.alkemy.java.exceptions;

public class NoAuthorizeException extends RuntimeException{

    private static final String description = "No Authorize Exception (401)";

    public NoAuthorizeException(String detail){
        super(NoAuthorizeException.description + " "+ detail);
    }
}
