package com.app.weatherserver.controller;

import com.app.weatherserver.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;


/**
 * Controller that handles exceptions thrown in the system
 *
 * @author Skripko Egor
 */
@RestControllerAdvice
public class ExceptionController {

    /**
     * Handle exceptions that occurred due to incorrect validation
     *
     * @param objException - thrown exception
     * @return <code>ResponseEntity</code> of {@link ApiErrorResponse}
     */
    @ExceptionHandler( MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiErrorResponse> handleWrongValidationException(MethodArgumentNotValidException objException) {
        ApiErrorResponse response = new ApiErrorResponse();
        objException.getBindingResult().getAllErrors().forEach((error) -> {
            response.addError(error.getDefaultMessage());
        });
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    /**
     * Handle exceptions that occurred due to entity existence errors
     *
     * @param objException - thrown exception
     * @return <code>ResponseEntity</code> of {@link ApiErrorResponse}
     */
    @ExceptionHandler({IllegalArgumentException.class, NoSuchElementException.class})
    protected ResponseEntity<ApiErrorResponse> handleEntityExistException(Exception objException) {
        ApiErrorResponse response = new ApiErrorResponse(objException.getLocalizedMessage());
        return new ResponseEntity<>(response,HttpStatus.CONFLICT);
    }
}
