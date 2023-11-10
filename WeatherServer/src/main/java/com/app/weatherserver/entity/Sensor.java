package com.app.weatherserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Class to represent sensor
 *
 * @author Skripko Egor
 */
@Entity
@Table
@Getter
@Setter
public class Sensor {

    /**
     * A value to represent sensor UUID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID key;

    /**
     * A value to represent sensor name
     */
    @Column(unique = true)
    private String name;

    /**
     * A value to represent sensor activity
     * If request from the sensor came at least a minute ago, then it is active and value is true
     * In reverse cases it is not active and value is false
     */
    private boolean isActive;
}
