package com.app.weathersensor.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent Data Transfer Object (DTO) of response to error that occurred on the server
 *
 * @author Skripko Egor
 */
@Getter
@Setter
public class ApiErrorResponse {
    /**
     * A value to represent all occurred errors messages
     */
    private List<String> errors;

    public ApiErrorResponse() {
        this.errors = new ArrayList<>();
    }
}
