package com.app.weathersensor.service;

import com.app.weathersensor.datacollector.WeatherDataCollector;
import com.app.weathersensor.dto.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * Service for sending requests to weather server
 *
 * @author Skripko Egor
 */
@Service
public class DataSenderService {
    /**
     * A value to represent weather server ip
     * Takes from application.properties
     */
    @Value("${weather.server.ip}")
    private String serverIp;

    /**
     * A value to represent weather sensor name
     * Takes from application.properties
     */
    @Value("${sensor.name}")
    private String name;

    /**
     * A value to represent weather sensor UUID
     */
    private UUID key;
    private final WeatherDataCollector dataCollector;
    private final ShutdownService shutdownService;
    private final ExceptionConverter exceptionConverter;

    public DataSenderService(WeatherDataCollector dataCollector, ShutdownService shutdownService, ExceptionConverter exceptionConverter) {
        this.dataCollector = dataCollector;
        this.shutdownService = shutdownService;
        this.exceptionConverter = exceptionConverter;
    }

    /**
     * Send request to weather server to registration itself
     * If sensor couldn't register then application shutdown
     *
     * @throws ResourceAccessException if server not start
     * @throws HttpClientErrorException if sensor with given name already exist
     */
    @PostConstruct
    public void registration() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        RegistrationRequest request = new RegistrationRequest();
        request.setName(name);

        try {
            ResponseEntity<RegistrationResponse> responseEntity = restTemplate.postForEntity(String.format("%s/sensors/registration", serverIp),request, RegistrationResponse.class);
            key = responseEntity.getBody().getKey();
        }
        catch (HttpClientErrorException e){
            throw exceptionConverter.convertErrorResponseToException(e);
        }
        catch (ResourceAccessException e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Send every 3-15 seconds weather measures to weather server
     *
     * @throws ResourceAccessException if server shutdown
     * @throws HttpClientErrorException if sensor with given key not exist
     */
    @Scheduled(fixedDelayString = "#{new Double((T(java.lang.Math).random() * 4 + 1) * 3000).intValue()}")
    public void sendData() throws Exception {
        if(key!=null) {
            RestTemplate restTemplate = new RestTemplate();
            MeasureWeatherRequest request = new MeasureWeatherRequest();
            request.setValue(dataCollector.getTemperature());
            request.setRaining(dataCollector.isRaining());

            try {
                restTemplate.postForEntity(String.format("%s/sensors/%s/measurements", serverIp, key), request, String.class);
            }
            catch (ResourceAccessException e){
                shutdownService.shutdownContext();
                throw new Exception(e.getMessage());
            }
            catch (HttpClientErrorException e){
                throw exceptionConverter.convertErrorResponseToException(e);
            }
        }
    }

}
