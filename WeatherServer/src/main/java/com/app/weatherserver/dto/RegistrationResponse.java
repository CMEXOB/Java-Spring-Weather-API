package com.app.weatherserver.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RegistrationResponse {
    private UUID key;

    public RegistrationResponse(UUID key) {
        this.key = key;
    }

    public RegistrationResponse() {
    }
}
