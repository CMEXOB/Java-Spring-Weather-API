package com.app.weatherserver.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Class to represent Data Transfer Object (DTO) of sensor registration request
 *
 * @author Skripko Egor
 */
@Getter
@Setter
public class RegistrationRequest {
    /**
     * A value to represent sensor name
     */
    @NotNull(message = "Name can't be empty")
    @Size(min=3, max = 30,message = "Name length should not be less than 3 and greet that 30\"")
    private String name;
}