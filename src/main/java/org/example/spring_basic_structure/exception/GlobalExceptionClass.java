package org.example.spring_basic_structure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionClass {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericExceptionClass> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<GenericExceptionClass.ValidationField> validationErrors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> new GenericExceptionClass.ValidationField(((FieldError) error).getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        GenericExceptionClass errorResponse = GenericExceptionClass.builder()
                .message("Invalid Input")
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST)
                .errors(validationErrors)
                .date(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NotFoundExceptionClass.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<GenericExceptionClass> handleNotFoundException(NotFoundExceptionClass ex) {
        GenericExceptionClass error = GenericExceptionClass.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .date(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    @ExceptionHandler(UnAuthorizedExceptionClass.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<GenericExceptionClass> handleUnAuthorizedException(UnAuthorizedExceptionClass ex) {
        GenericExceptionClass error = GenericExceptionClass.builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .status(HttpStatus.UNAUTHORIZED)
                .message(ex.getMessage())
                .date(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }


    @ExceptionHandler(BadRequestExceptionClass.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<GenericExceptionClass> handleBadRequestException(BadRequestExceptionClass ex) {
        GenericExceptionClass error = GenericExceptionClass.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .date(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
