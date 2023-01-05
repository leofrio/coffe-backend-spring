package com.CoffeControl.backend.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
public class ExceptionResponse {
    private LocalDate timestamp;
    private String title;
    private String message;
    private HttpStatus httpStatus;

    public ExceptionResponse(LocalDate timestamp, String message, String title, HttpStatus httpStatus) {
        this.timestamp = timestamp;
        this.title = title;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
