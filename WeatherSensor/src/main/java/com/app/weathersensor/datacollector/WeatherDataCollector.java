package com.app.weathersensor.datacollector;

/**
 * Interface class that has methods for weather measures
 *
 * @author Skripko Egor
 */
public interface WeatherDataCollector {
    /**
     * Return weather temperature
     *
     * @return double
     */
    double getTemperature();

    /**
     * Return value that represent if it is raining
     *
     * @return true if it is raining and false in reverse cases
     */
    boolean isRaining();
}
