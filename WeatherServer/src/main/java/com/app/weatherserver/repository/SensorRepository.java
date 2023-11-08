package com.app.weatherserver.repository;

import com.app.weatherserver.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, String> {
    boolean existsByName(String name);
}
