package com.app.weathersensor.datacollector;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SimpleWeatherDataCollector implements WeatherDataCollector{
    private final Random rm;

    public SimpleWeatherDataCollector() {
        rm = new Random();
    }

    @Override
    public double getTemperature() {
        int max = 100;
        int min = -100;
        return rm.nextDouble()*(max - min) + min;
    }

    @Override
    public boolean isRaining() {
        return rm.nextBoolean();
    }
}
