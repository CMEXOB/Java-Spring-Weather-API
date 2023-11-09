package com.app.weatherserver.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ErrorResponse implements WeatherServerResponse {
    private List<String> errors;

    public ErrorResponse() {
        errors = new ArrayList<>();
    }
    public ErrorResponse(String error) {
        errors = new ArrayList<>();
        errors.add(error);
    }

    public void addError(String error){
        errors.add(error);
    }
}
