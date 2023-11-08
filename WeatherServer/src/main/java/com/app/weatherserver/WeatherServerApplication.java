package com.app.weatherserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.app.weatherserver.entity")
public class WeatherServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherServerApplication.class, args);
	}

}
