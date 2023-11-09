package com.app.weatherserver.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeasureWeatherRequest {
    @NotNull(message = "Raining can't be empty")
    @Min(value = -100, message = "Temperature should not be less than -100")
    @Max(value = 100, message = "Temperature should not be greater than 100")
    private Double value;

    @NotNull(message = "Raining can't be empty")
    private Boolean raining;
}