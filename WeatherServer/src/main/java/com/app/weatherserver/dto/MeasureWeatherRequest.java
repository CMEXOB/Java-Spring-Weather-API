package com.app.weatherserver.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Class to represent Data Transfer Object (DTO) of sensor weather measure request
 *
 * @author Skripko Egor
 */
@Getter
@Setter
public class MeasureWeatherRequest {

    /**
     * A value to represent temperature
     */
    @NotNull(message = "Raining can't be empty")
    @Min(value = -100, message = "Temperature should not be less than -100")
    @Max(value = 100, message = "Temperature should not be greater than 100")
    private Double value;

    /**
     * A value to represent whether it is raining
     */
    @NotNull(message = "Raining can't be empty")
    private Boolean raining;
}