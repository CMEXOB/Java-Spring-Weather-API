package com.app.weatherserver.service;

import com.app.weatherserver.dto.RegistrationResponse;
import com.app.weatherserver.entity.Sensor;
import com.app.weatherserver.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public RegistrationResponse registerSensor(String sensorName) {
        if(sensorRepository.existsByName(sensorName)){
            throw new IllegalArgumentException (String.format("Sensor with name '%s' already exist", sensorName));
        }
        else {
            Sensor sensor = new Sensor();
            sensor.setName(sensorName);
            sensorRepository.save(sensor);
            return new RegistrationResponse(sensor.getKey());
        }
    }

    public List<Sensor> getSensors() {
        return sensorRepository.findAllByIsActiveIsTrue();
    }
}
