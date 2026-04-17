package com.sweethome.backend.controller;

import com.sweethome.backend.service.HouseService;
import com.sweethome.backend.service.dto.HouseDto;
import com.sweethome.backend.service.dto.HousePatchDto;
import com.sweethome.backend.service.dto.PropertyResponseDto;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/houses")
public class HouseController implements SecuredRestController {
    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    private static final Logger logger = LoggerFactory.getLogger(HouseController.class);

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<PropertyResponseDto> create(@Valid @RequestBody HouseDto dto) {
        logger.info("Request to create property listing: {}", dto);

        PropertyResponseDto saved = houseService.save(dto);

        logger.info("Successfully created property listing with id: {}", saved.getId());
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@propertyAuthorization.canModifyProperty(#id)")
    public ResponseEntity<PropertyResponseDto> update(@PathVariable UUID id, @Valid @RequestBody HouseDto dto) {
        logger.info("Request to update property listing with id: {} and payload: {}", id, dto);

        PropertyResponseDto updated = houseService.update(id, dto);

        logger.info("Successfully updated property listing with id: {}", id);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("@propertyAuthorization.canModifyProperty(#id)")
    public ResponseEntity<PropertyResponseDto> patch(@PathVariable UUID id, @Valid @RequestBody HousePatchDto dto) {
        logger.info("Request to patch property listing with id: {} and payload: {}", id, dto);

        PropertyResponseDto updated = houseService.patch(id, dto);

        logger.info("Successfully patched property listing with id: {}", id);
        return ResponseEntity.ok(updated);
    }
}
