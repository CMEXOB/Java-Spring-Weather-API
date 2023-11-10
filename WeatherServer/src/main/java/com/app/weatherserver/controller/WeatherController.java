package com.app.weatherserver.controller;

import com.app.weatherserver.dto.ApiErrorResponse;
import com.app.weatherserver.dto.MeasureWeatherRequest;
import com.app.weatherserver.entity.Weather;
import com.app.weatherserver.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Save sensor weather measure", description = "Save weather measurements of sensor with given UUID key")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful registration and UUID key provision", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request - The weather measurements data not valid",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Conflict - The sensor with given name not exist",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PostMapping("{key}/measurements")
    public ResponseEntity measureWeatherFromSensor(@Parameter(name = "key", description = "Sensor unique UUID", example = "a0fbb0de-62e5-4370-8bbe-a58242dc80a7") @PathVariable("key") UUID key,
                                                   @Valid @RequestBody MeasureWeatherRequest measureWeatherRequest){
        weatherService.measureWeatherFromSensor(key, measureWeatherRequest.getValue(), measureWeatherRequest.getRaining());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @Operation(summary = "Get sensor weather measurements", description = "Returns 20 latest weather measurements of sensor with given UUID key")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "409", description = "Conflict - The sensor with given name not exist",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @GetMapping("{key}/measurements")
    public List<Weather> getWeatherFromSensor(@Parameter(name = "key", description = "Sensor unique UUID", example = "a0fbb0de-62e5-4370-8bbe-a58242dc80a7") @PathVariable("key") UUID key){
        return weatherService.getWeatherFromSensor(key);
    }
    @Operation(summary = "Get current weather measurements", description = "Returns weather measurements which were made no more than a minute ago")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    })
    @GetMapping("measurements")
    public List<Weather> getCurrentWeatherFromSensor(){
        return weatherService.getCurrentWeatherFromSensor();
    }
}
