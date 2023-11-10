package com.app.weatherserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

/**
 * Class to represent weather measurements
 *
 * @author Skripko Egor
 */
@Entity
@Table
@Getter
@Setter
public class Weather {


    @Id
    @SequenceGenerator(
            name="weather_sequence",
            sequenceName = "weather_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "weather_sequence"
    )
    private Long id;

    /**
     * A value to represent temperature
     */
    private double value;

    /**
     * A value to represent whether it is raining
     */
    private boolean isRaining;

    /**
     * A value to represent time when measurements were taken
     */
    private Time time;

    /**
     * A value to represent {@link Sensor} that made the measurement
     */
    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;
}
