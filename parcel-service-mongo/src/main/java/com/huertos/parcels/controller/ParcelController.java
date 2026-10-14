package com.huertos.parcels.controller;

import com.huertos.parcels.dto.ParcelRequest;
import com.huertos.parcels.dto.ParcelResponse;
import com.huertos.parcels.service.ParcelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parcels")
public class ParcelController {

    private final ParcelService parcelService;

    public ParcelController(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    @PostMapping
    public ResponseEntity<ParcelResponse> create(@Valid @RequestBody ParcelRequest request) {
        ParcelResponse created = parcelService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<ParcelResponse>> findAll() {
        return ResponseEntity.ok(parcelService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParcelResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(parcelService.findById(id));
    }
}
