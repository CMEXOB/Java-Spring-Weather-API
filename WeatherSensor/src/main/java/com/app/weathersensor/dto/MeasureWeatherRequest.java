package com.app.weathersensor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeasureWeatherRequest {
    private Double value;
    private Boolean raining;
}