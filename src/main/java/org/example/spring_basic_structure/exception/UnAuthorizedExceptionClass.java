package org.example.spring_basic_structure.exception;

public class UnAuthorizedExceptionClass extends RuntimeException{
    public UnAuthorizedExceptionClass(String message){
        super(message);
    }
}
