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

@Service
public class DataSenderService {
    @Value("${weather.server.ip}")
    private String serverIp;
    @Value("${sensor.name}")
    private String name;
    private UUID key;
    private final WeatherDataCollector dataCollector;
    private final ShutdownService shutdownService;

    public DataSenderService(WeatherDataCollector dataCollector, ShutdownService shutdownService) {
        this.dataCollector = dataCollector;
        this.shutdownService = shutdownService;
    }

    @PostConstruct
    public void registration() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        RegistrationRequest request = new RegistrationRequest();
        request.setName(name);

        try {
            ResponseEntity<RegistrationResponse> responseEntity = restTemplate.postForEntity(String.format("%s/sensors/registration", serverIp),request, RegistrationResponse.class);
            key = responseEntity.getBody().getKey();
        }
        catch (ResourceAccessException | HttpClientErrorException | NullPointerException e){
            throw new Exception(e.getMessage());
        }
    }

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
                throw new Exception(e.getMessage());
            }
        }
    }

}
