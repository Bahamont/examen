package com.huertos.parcels.repository;

import com.huertos.parcels.domain.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ParcelRepository extends JpaRepository<Parcel, UUID> {

    boolean existsByNameIgnoreCase(String name);

    Optional<Parcel> findByNameIgnoreCase(String name);
}
