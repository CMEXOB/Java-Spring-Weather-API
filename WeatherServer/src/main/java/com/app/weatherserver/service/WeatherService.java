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

@Service
public class WeatherService {
    private final WeatherRepository weatherRepository;
    private final SensorRepository sensorRepository;

    public WeatherService(WeatherRepository weatherRepository, SensorRepository sensorRepository) {
        this.weatherRepository = weatherRepository;
        this.sensorRepository = sensorRepository;
    }
    public void measureWeatherFromSensor(UUID key, double value, boolean raining){
        Optional<Sensor> sensor = sensorRepository.findById(key);
        if(sensor.isPresent()){
            Weather weather = new Weather();
            weather.setValue(value);
            weather.setRaining(raining);
            weather.setTime(Time.valueOf(LocalTime.now()));
            weather.setCreator(sensor.get());
            weatherRepository.save(weather);
        }
        else {
            throw new NoSuchElementException(String.format("Sensor with key '%s' don't exist", key));
        }

    }

    public List<Weather> getWeatherFromSensor(UUID key){
        Optional<Sensor> sensor = sensorRepository.findById(key);
        if(sensor.isPresent()){
            return weatherRepository.findWeatherFromSensor(sensor.get());
        }
        else {
            throw new NoSuchElementException(String.format("Sensor with key '%s' don't exist", key));
        }
    }

    public List<Weather> getCurrentWeatherFromSensor() {
        Time time = Time.valueOf(LocalTime.now());
        time.setTime(time.getTime() - 60000);
        return weatherRepository.findCurrentWeatherFromSensor(time);
    }

}
