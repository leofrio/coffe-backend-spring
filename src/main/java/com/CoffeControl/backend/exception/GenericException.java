package com.CoffeControl.backend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Getter
public class GenericException extends Exception{

    private ExceptionResponse exception;

    public GenericException(String title, String exception, HttpStatus status) {
        super(exception);
        this.exception = new ExceptionResponse(LocalDate.now(), exception, title, status);
    }

}
