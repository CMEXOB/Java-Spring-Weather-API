package com.app.weathersensor.dto;

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
    private Double value;

    /**
     * A value to represent whether it is raining
     */
    private Boolean raining;
}