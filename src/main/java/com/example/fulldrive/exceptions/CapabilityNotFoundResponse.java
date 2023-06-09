package com.example.fulldrive.exceptions;

import lombok.Getter;

@Getter
public class CapabilityNotFoundResponse {
    private String capabilityNotFound;

    public CapabilityNotFoundResponse(String capabilityNotFound) {
        this.capabilityNotFound = capabilityNotFound;
    }
}
