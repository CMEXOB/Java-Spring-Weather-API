package com.app.weatherserver.controller;

import com.app.weatherserver.dto.MeasureWeatherRequest;
import com.app.weatherserver.entity.Weather;
import com.app.weatherserver.service.WeatherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sensors")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
    @PostMapping("{key}/measurements")
    public ResponseEntity measureWeatherFromSensor(@PathVariable UUID key, @Valid @RequestBody MeasureWeatherRequest measureWeatherRequest){
        weatherService.measureWeatherFromSensor(key, measureWeatherRequest.getValue(), measureWeatherRequest.getRaining());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("{key}/measurements")
    public List<Weather> getWeatherFromSensor(@PathVariable UUID key){
        return weatherService.getWeatherFromSensor(key);
    }
    @GetMapping("measurements")
    public List<Weather> getCurrentWeatherFromSensor(){
        return weatherService.getCurrentWeatherFromSensor();
    }
}
