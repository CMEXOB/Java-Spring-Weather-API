package com.app.weatherserver.controller;

import com.app.weatherserver.dto.ErrorResponse;
import com.app.weatherserver.dto.WeatherServerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;


@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler( MethodArgumentNotValidException.class)
    protected ResponseEntity<WeatherServerResponse> handleWrongValidationException(MethodArgumentNotValidException objException) {
        ErrorResponse response = new ErrorResponse();
        objException.getBindingResult().getAllErrors().forEach((error) -> {
            response.addError(error.getDefaultMessage());
        });
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IllegalAccessError.class, NoSuchElementException.class})
    protected ResponseEntity<WeatherServerResponse> handleEntityExistException(Exception objException) {
        ErrorResponse response = new ErrorResponse();
        response.addError(objException.getLocalizedMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
