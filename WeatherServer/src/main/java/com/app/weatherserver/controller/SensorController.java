package com.app.weatherserver.controller;

import com.app.weatherserver.dto.ApiErrorResponse;
import com.app.weatherserver.dto.RegistrationRequest;
import com.app.weatherserver.dto.RegistrationResponse;
import com.app.weatherserver.entity.Sensor;
import com.app.weatherserver.service.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Register a sensor", description = "Registers a sensor with a unique name and returns UUID key")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful registration and UUID key provision"),
            @ApiResponse(responseCode = "400", description = "Bad request - The sensor name not valid",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Conflict - The sensor with given name already exist",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PostMapping("registration")
    public ResponseEntity<RegistrationResponse> registerSensor(@Valid @RequestBody RegistrationRequest request){
        RegistrationResponse response = sensorService.registerSensor(request.getName());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @Operation(summary = "Get active sensors", description = "Returns a sensors that which are active")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    })
    @GetMapping
    public List<Sensor> getSensors(){
        return sensorService.getSensors();
    }
}
