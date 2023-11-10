package com.app.weatherserver.controller;

import com.app.weatherserver.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;


@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler( MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiErrorResponse> handleWrongValidationException(MethodArgumentNotValidException objException) {
        ApiErrorResponse response = new ApiErrorResponse();
        objException.getBindingResult().getAllErrors().forEach((error) -> {
            response.addError(error.getDefaultMessage());
        });
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({IllegalArgumentException.class, NoSuchElementException.class})
    protected ResponseEntity<ApiErrorResponse> handleEntityExistException(Exception objException) {
        ApiErrorResponse response = new ApiErrorResponse(objException.getLocalizedMessage());
        return new ResponseEntity<>(response,HttpStatus.CONFLICT);
    }
}
