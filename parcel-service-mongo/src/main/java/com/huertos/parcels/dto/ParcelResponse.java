package com.huertos.parcels.dto;

import com.huertos.parcels.domain.Location;
import com.huertos.parcels.domain.Parcel;
import com.huertos.parcels.domain.SoilType;

import java.time.Instant;

public class ParcelResponse {

    private String id;
    private String name;
    private Location location;
    private SoilType soilType;
    private String ownerOrLead;
    private Instant createdAt;
    private String status;

    public static ParcelResponse from(Parcel parcel) {
        ParcelResponse response = new ParcelResponse();
        response.setId(parcel.getId());
        response.setName(parcel.getName());
        response.setLocation(parcel.getLocation());
        response.setSoilType(parcel.getSoilType());
        response.setOwnerOrLead(parcel.getOwnerOrLead());
        response.setCreatedAt(parcel.getCreatedAt());
        response.setStatus("ACTIVA");
        return response;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
