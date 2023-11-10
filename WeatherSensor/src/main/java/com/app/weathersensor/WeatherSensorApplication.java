package com.app.weathersensor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeatherSensorApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherSensorApplication.class, args);
    }

}
