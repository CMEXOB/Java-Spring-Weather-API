package com.app.weatherserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

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
    private double value;
    private boolean isRaining;
    private Time time;
    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor creator;
}
