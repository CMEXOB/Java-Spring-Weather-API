package com.app.weatherserver.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Class to represent Data Transfer Object (DTO) of server registration response
 *
 * @author Skripko Egor
 */
@Getter
@Setter
public class RegistrationResponse {

    /**
     * A value to represent sensor UUID
     */
    private UUID key;

    public RegistrationResponse(UUID key) {
        this.key = key;
    }

    public RegistrationResponse() {
    }
}
