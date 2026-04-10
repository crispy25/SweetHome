package com.sweethome.backend.controller;

import com.sweethome.backend.service.ApartmentService;
import com.sweethome.backend.service.dto.ApartmentDto;
import com.sweethome.backend.service.dto.ApartmentPatchDto;
import com.sweethome.backend.service.dto.ApartmentResponseDto;
import com.sweethome.backend.service.dto.PropertyResponseDto;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/apartments")
public class ApartmentController implements SecuredRestController {
    private final ApartmentService apartmentService;

    private static final Logger logger = LoggerFactory.getLogger(ApartmentController.class);

    @Autowired
    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @PostMapping(path = "/add")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<PropertyResponseDto> create(@Valid @RequestBody ApartmentDto dto) {
        logger.info("Request to create property listing: {}", dto);

        PropertyResponseDto saved = apartmentService.save(dto);

        logger.info("Successfully created property listing with id: {}", saved.getId());
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<PropertyResponseDto> update(@PathVariable UUID id, @Valid @RequestBody ApartmentDto dto) {
        logger.info("Request to update property listing with id: {} and payload: {}", id, dto);

        PropertyResponseDto updated = apartmentService.update(id, dto);

        logger.info("Successfully updated property listing with id: {}", id);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/patch/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<PropertyResponseDto> patch(@PathVariable UUID id, @Valid @RequestBody ApartmentPatchDto dto) {
        logger.info("Request to patch property listing with id: {} and payload: {}", id, dto);

        PropertyResponseDto updated = apartmentService.patch(id, dto);

        logger.info("Successfully patched property listing with id: {}", id);
        return ResponseEntity.ok(updated);
    }
}
