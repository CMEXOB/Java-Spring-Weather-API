package com.app.weathersensor.dto;

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
    private String name;
}