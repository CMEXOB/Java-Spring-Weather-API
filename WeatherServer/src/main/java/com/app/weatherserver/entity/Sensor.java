package com.app.weatherserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table
@Getter
@Setter
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID key;

    @Column(unique = true)
    private String name;
}
