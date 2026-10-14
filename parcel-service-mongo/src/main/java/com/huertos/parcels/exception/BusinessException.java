package com.huertos.parcels.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    private final String messageKey;
    private final Object[] args;
    private final HttpStatus status;

    public BusinessException(String messageKey, HttpStatus status, Object... args) {
        super(messageKey);
        this.messageKey = messageKey;
        this.status = status;
        this.args = args;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public Object[] getArgs() {
        return args;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
