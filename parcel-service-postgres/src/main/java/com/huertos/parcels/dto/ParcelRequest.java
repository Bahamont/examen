package com.huertos.parcels.dto;

import com.huertos.parcels.domain.SoilType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ParcelRequest {

    @NotBlank(message = "{parcel.name.required}")
    @Size(min = 3, max = 100, message = "{parcel.name.size}")
    @Pattern(
            regexp = "^[\\p{L}\\p{N}][\\p{L}\\p{N}\\s.\\-_]*$",
            message = "{parcel.name.pattern}"
    )
    private String name;

    @NotNull(message = "{parcel.location.required}")
    @Valid
    private LocationRequest location;

    @NotNull(message = "{parcel.soilType.required}")
    private SoilType soilType;

    @NotBlank(message = "{parcel.owner.required}")
    @Size(min = 3, max = 120, message = "{parcel.owner.size}")
    private String ownerOrLead;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationRequest getLocation() {
        return location;
    }

    public void setLocation(LocationRequest location) {
        this.location = location;
    }

    public SoilType getSoilType() {
        return soilType;
    }

    public void setSoilType(SoilType soilType) {
        this.soilType = soilType;
    }

    public String getOwnerOrLead() {
        return ownerOrLead;
    }

    public void setOwnerOrLead(String ownerOrLead) {
        this.ownerOrLead = ownerOrLead;
    }
}
