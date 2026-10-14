package com.huertos.parcels.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum SoilType {
    ARCILLOSO,
    LIMOSO,
    ARENOSO;

    @JsonCreator
    public static SoilType from(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return SoilType.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Invalid soil type: " + value, ex);
        }
    }
}
