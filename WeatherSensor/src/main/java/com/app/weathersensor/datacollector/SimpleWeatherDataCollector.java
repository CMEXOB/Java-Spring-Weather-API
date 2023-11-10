package com.app.weathersensor.datacollector;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Class that implements {@link WeatherDataCollector}
 * Weather measures are created randomly
 *
 * @author Skripko Egor
 */
@Component
public class SimpleWeatherDataCollector implements WeatherDataCollector{
    /**
     * Object for creating random value
     */
    private final Random rm;

    public SimpleWeatherDataCollector() {
        rm = new Random();
    }

    /**
     * Return random value in range from -100 to 100
     *
     * @return double
     */
    @Override
    public double getTemperature() {
        int max = 100;
        int min = -100;
        return rm.nextDouble()*(max - min) + min;
    }

    /**
     * Return random true or false
     *
     * @return boolean
     */
    @Override
    public boolean isRaining() {
        return rm.nextBoolean();
    }
}
