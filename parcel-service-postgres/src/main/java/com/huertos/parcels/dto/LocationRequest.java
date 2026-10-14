package com.huertos.parcels.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class LocationRequest {

    @NotNull(message = "{parcel.location.latitude.required}")
    @DecimalMin(value = "-90.0", message = "{parcel.location.latitude.range}")
    @DecimalMax(value = "90.0", message = "{parcel.location.latitude.range}")
    private Double latitude;

    @NotNull(message = "{parcel.location.longitude.required}")
    @DecimalMin(value = "-180.0", message = "{parcel.location.longitude.range}")
    @DecimalMax(value = "180.0", message = "{parcel.location.longitude.range}")
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
