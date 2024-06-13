package org.example.spring_basic_structure.exception;

public class BadRequestExceptionClass extends RuntimeException{
    public BadRequestExceptionClass(String message){
        super(message);
    }
}
