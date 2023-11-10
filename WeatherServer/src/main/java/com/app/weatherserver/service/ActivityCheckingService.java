package com.app.weatherserver.service;

import com.app.weatherserver.entity.Sensor;
import com.app.weatherserver.entity.Weather;
import com.app.weatherserver.repository.SensorRepository;
import com.app.weatherserver.repository.WeatherRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service for checking {@link Sensor} activity in system
 *
 * @author Skripko Egor
 */
@Service
public class ActivityCheckingService {
    private final WeatherRepository weatherRepository;
    private final SensorRepository sensorRepository;

    public ActivityCheckingService(WeatherRepository weatherRepository, SensorRepository sensorRepository) {
        this.weatherRepository = weatherRepository;
        this.sensorRepository = sensorRepository;
    }

    /**
     * Checking periodically all sensors for activity.
     */
    @Scheduled(fixedDelay  = 1000)
    public void scheduleFixedRateTask() {
        Time time = Time.valueOf(LocalTime.now());
        time.setTime(time.getTime() - 60000);
        Set<Sensor> sensors = weatherRepository.findWeatherSensorWitchNeedDeactivated(time);
        for(Sensor sensor: sensors){
            sensor.setActive(false);
            sensorRepository.save(sensor);
        }
    }
}

