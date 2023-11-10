package com.app.weatherserver.service;

import com.app.weatherserver.entity.Sensor;
import com.app.weatherserver.entity.Weather;
import com.app.weatherserver.repository.SensorRepository;
import com.app.weatherserver.repository.WeatherRepository;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

/**
 * Service to save sensor weather measure and get information about {@link Weather} in system
 *
 * @author Skripko Egor
 */
@Service
public class WeatherService {
    private final WeatherRepository weatherRepository;
    private final SensorRepository sensorRepository;

    public WeatherService(WeatherRepository weatherRepository, SensorRepository sensorRepository) {
        this.weatherRepository = weatherRepository;
        this.sensorRepository = sensorRepository;
    }

    /**
     * Save new sensor weather measure to database. Update sensor active
     *
     * @param key - {@link Sensor} UUID
     * @param value -  new {@link Weather} value
     * @param raining - new {@link Weather} isRaining
     */
    public void measureWeatherFromSensor(UUID key, double value, boolean raining){
        Optional<Sensor> sensor = sensorRepository.findById(key);
        if(sensor.isPresent()){
            sensor.get().setActive(true);
            sensorRepository.save(sensor.get());

            Weather weather = new Weather();
            weather.setValue(value);
            weather.setRaining(raining);
            weather.setTime(Time.valueOf(LocalTime.now()));
            weather.setSensor(sensor.get());
            weatherRepository.save(weather);
        }
        else {
            throw new NoSuchElementException(String.format("Sensor with key '%s' don't exist", key));
        }

    }

    /**
     * Return {@link Weather} which belong to {@link Sensor} with given UUID
     *
     * @param key - given {@link Sensor} UUID
     * @return <code>List</code> of {@link Weather}
     */
    public List<Weather> getWeatherFromSensor(UUID key){
        Optional<Sensor> sensor = sensorRepository.findById(key);
        if(sensor.isPresent()){
            return weatherRepository.findWeatherBySensor(sensor.get());
        }
        else {
            throw new NoSuchElementException(String.format("Sensor with key '%s' don't exist", key));
        }
    }

    /**
     * Return {@link Weather} which time later that minute ago
     *
     * @return <code>List</code> of {@link Weather}
     */
    public List<Weather> getCurrentWeatherFromSensor() {
        Time time = Time.valueOf(LocalTime.now());
        time.setTime(time.getTime() - 60000);
        return weatherRepository.findWeatherLaterThatTime(time);
    }

}
