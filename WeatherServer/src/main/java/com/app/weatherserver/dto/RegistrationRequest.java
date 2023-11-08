package com.app.weatherserver.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    @NotNull(message = "Name can't be empty")
    @Size(min=3, max = 30,message = "wrong size")
    private String name;
}