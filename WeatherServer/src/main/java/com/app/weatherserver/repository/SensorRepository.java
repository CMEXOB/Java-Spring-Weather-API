package com.app.weatherserver.repository;

import com.app.weatherserver.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Repository class to manage {@link Sensor} instances
 *
 * @author Skripko Egor
 */
public interface SensorRepository extends JpaRepository<Sensor, UUID> {
    /**
     * Checks for existence of {@link Sensor} by given name
     *
     * @param name - given {@link Sensor} name
     * @return true if {@link Sensor} exist and false if not
     */
    boolean existsByName(String name);

    /**
     * Retrieve all {@link Sensor} which are active
     *
     * @return <code>List</code> of {@link Sensor}
     */
    List<Sensor> findAllByIsActiveIsTrue();
}
