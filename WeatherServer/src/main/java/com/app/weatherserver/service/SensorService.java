package com.app.weatherserver.service;

import com.app.weatherserver.dto.RegistrationResponse;
import com.app.weatherserver.entity.Sensor;
import com.app.weatherserver.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to register and get information about {@link Sensor} in system
 *
 * @author Skripko Egor
 */
@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    /**
     * Registers new {@link Sensor} in system
     *
     * @param sensorName - given name, must not be {@literal null}
     * @return {@link RegistrationResponse}
     * @throws IllegalArgumentException - If {@link Sensor} with given name already exist
     */
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

    /**
     * Return list of active {@link Sensor}
     *
     * @return <code>List</code> of {@link Sensor}
     */
    public List<Sensor> getSensors() {
        return sensorRepository.findAllByIsActiveIsTrue();
    }
}
