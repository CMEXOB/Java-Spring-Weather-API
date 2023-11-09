package com.app.weatherserver.repository;

import com.app.weatherserver.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SensorRepository extends JpaRepository<Sensor, UUID> {
    boolean existsByName(String name);
    List<Sensor> findAllByIsActiveIsTrue();
}
