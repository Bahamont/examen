package com.huertos.parcels.service;

import com.huertos.parcels.domain.Location;
import com.huertos.parcels.domain.Parcel;
import com.huertos.parcels.dto.ParcelRequest;
import com.huertos.parcels.dto.ParcelResponse;
import com.huertos.parcels.exception.DuplicateResourceException;
import com.huertos.parcels.exception.ResourceNotFoundException;
import com.huertos.parcels.repository.ParcelRepository;
import com.huertos.parcels.util.InputSanitizer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class ParcelService {

    private final ParcelRepository parcelRepository;

    public ParcelService(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    @Transactional
    public ParcelResponse create(ParcelRequest request) {
        ParcelRequest sanitized = sanitize(request);

        if (parcelRepository.existsByNameIgnoreCase(sanitized.getName())) {
            throw new DuplicateResourceException("error.parcel.duplicateName", sanitized.getName());
        }

        Parcel parcel = new Parcel();
        parcel.setName(sanitized.getName());
        parcel.setLocation(new Location(
                sanitized.getLocation().getLatitude(),
                sanitized.getLocation().getLongitude()
        ));
        parcel.setSoilType(sanitized.getSoilType());
        parcel.setOwnerOrLead(sanitized.getOwnerOrLead());
        parcel.setCreatedAt(Instant.now());

        return ParcelResponse.from(parcelRepository.save(parcel));
    }

    @Transactional(readOnly = true)
    public List<ParcelResponse> findAll() {
        return parcelRepository.findAll().stream()
                .map(ParcelResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public ParcelResponse findById(UUID id) {
        Parcel parcel = parcelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("error.parcel.notFound", id));
        return ParcelResponse.from(parcel);
    }

    private ParcelRequest sanitize(ParcelRequest request) {
        request.setName(InputSanitizer.sanitizePlainText(request.getName()));
        request.setOwnerOrLead(InputSanitizer.sanitizePlainText(request.getOwnerOrLead()));
        return request;
    }
}
