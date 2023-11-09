package com.app.weatherserver.repository;

import com.app.weatherserver.entity.Sensor;
import com.app.weatherserver.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
    @Query("select w from Weather w where w.creator = ?1 order by w.time desc limit 20")
    List<Weather> findWeatherFromSensor(Sensor key);
    @Query("select w from Weather w where w.time >= ?1")
    List<Weather> findCurrentWeatherFromSensor(Time time);
    Optional<Weather> findWeatherByCreatorOrderByTimeDesc(Sensor sensor);
}
