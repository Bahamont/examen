package com.huertos.parcels.repository;

import com.huertos.parcels.domain.Parcel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ParcelRepository extends MongoRepository<Parcel, String> {

    boolean existsByNameIgnoreCase(String name);

    Optional<Parcel> findByNameIgnoreCase(String name);
}
