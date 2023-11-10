package com.app.weathersensor.service;

import com.app.weathersensor.dto.ApiErrorResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Component for converting to Exception
 *
 * @author Skripko Egor
 */
@Component
public class ExceptionConverter {
    /**
     * Convert error response that send weather server to exception
     * @param exception - exception that throw RestTemplate because of 4xx http status
     * @throws Exception - converted Exception
     */
    public Exception convertErrorResponseToException(HttpClientErrorException exception){
        ApiErrorResponse response = exception.getResponseBodyAs(ApiErrorResponse.class);
        StringBuilder sb = new StringBuilder();
        for(String error: response.getErrors()){
            sb.append(error);
            sb.append(" ");
        }
        return new Exception(sb.toString());
    }
}
