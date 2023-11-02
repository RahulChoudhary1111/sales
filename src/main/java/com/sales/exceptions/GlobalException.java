package com.sales.exceptions;

import com.sales.dto.ErrorDto;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalException {


    @ExceptionHandler(value = {ObjectNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDto resourceNotFoundException(ObjectNotFoundException ex, WebRequest request) {
        ErrorDto message = new ErrorDto(ex.getMessage(),404);
        ex.printStackTrace();
        return message;
    }


    @ExceptionHandler(value = {SQLIntegrityConstraintViolationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDto resourceNotFoundException(SQLIntegrityConstraintViolationException ex, WebRequest request) {
        ErrorDto message = new ErrorDto(ex.getMessage(),500);
        ex.printStackTrace();
        return message;
    }

    @ExceptionHandler(value = {NullPointerException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDto resourceNotFoundException(NullPointerException ex, WebRequest request) {
        ErrorDto message = new ErrorDto("Something went wrong there  is a null pointer exception.",500);
        ex.printStackTrace();
        return message;
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto resourceNotFoundException(Exception ex, WebRequest request) {
        ErrorDto message = new ErrorDto(ex.getMessage(),500);
        ex.printStackTrace();
        return message;
    }

}
