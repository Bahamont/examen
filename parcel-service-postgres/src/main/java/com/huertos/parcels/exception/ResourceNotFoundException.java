package com.huertos.parcels.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BusinessException {

    public ResourceNotFoundException(String messageKey, Object... args) {
        super(messageKey, HttpStatus.NOT_FOUND, args);
    }
}
