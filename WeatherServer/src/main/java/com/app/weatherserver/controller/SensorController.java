package com.app.weatherserver.controller;

import com.app.weatherserver.dto.RegistrationRequest;
import com.app.weatherserver.dto.WeatherServerResponse;
import com.app.weatherserver.entity.Sensor;
import com.app.weatherserver.service.SensorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping("registration")
    public ResponseEntity<WeatherServerResponse> registerSensor(@Valid @RequestBody RegistrationRequest request){
        WeatherServerResponse response = sensorService.registerSensor(request.getName());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping
    public List<Sensor> getSensors(){
        return sensorService.getSensors();
    }
}
