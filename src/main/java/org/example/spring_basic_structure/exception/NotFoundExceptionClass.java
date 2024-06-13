package org.example.spring_basic_structure.exception;

public class NotFoundExceptionClass extends RuntimeException{
    public NotFoundExceptionClass(String message){
        super(message);
    }
}
