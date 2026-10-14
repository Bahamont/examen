package com.huertos.parcels.exception;

import org.springframework.http.HttpStatus;

public class DuplicateResourceException extends BusinessException {

    public DuplicateResourceException(String messageKey, Object... args) {
        super(messageKey, HttpStatus.CONFLICT, args);
    }
}
