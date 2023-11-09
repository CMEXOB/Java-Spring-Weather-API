package com.app.weatherserver.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ApiErrorResponse {
    private List<String> errors;

    public ApiErrorResponse() {
        errors = new ArrayList<>();
    }
    public ApiErrorResponse(String error) {
        errors = new ArrayList<>();
        errors.add(error);
    }

    public void addError(String error){
        errors.add(error);
    }
}
