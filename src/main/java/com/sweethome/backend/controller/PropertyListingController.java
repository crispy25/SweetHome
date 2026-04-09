package com.sweethome.backend.controller;

import com.sweethome.backend.entity.PropertyListing;
import com.sweethome.backend.service.PropertyListingService;
import com.sweethome.backend.service.dto.PropertyListingDto;
import com.sweethome.backend.service.dto.PropertyListingPatchDto;
import com.sweethome.backend.service.dto.PropertyListingResponseDto;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public abstract class PropertyListingController<CREATE_DTO extends PropertyListingDto,
                                                PATCH_DTO extends PropertyListingPatchDto,
                                                RESPONSE_DTO extends PropertyListingResponseDto> implements SecuredRestController {

    protected abstract PropertyListingService<? extends PropertyListing, CREATE_DTO, PATCH_DTO, RESPONSE_DTO> getService();

    private static final Logger logger = LoggerFactory.getLogger(PropertyListingController.class);

    @PostMapping(path = "/add")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<RESPONSE_DTO> create(@Valid @RequestBody CREATE_DTO dto) {
        logger.info("Request to create property listing: {}", dto);

        RESPONSE_DTO saved = getService().save(dto);

        logger.info("Successfully created property listing with id: {}", saved.getId());
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<RESPONSE_DTO> getById(@PathVariable UUID id) {
        logger.info("Request to get property listing with id: {}", id);

        RESPONSE_DTO dto = getService().getById(id);

        logger.info("Successfully retrieved property listing with id: {}", id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(path = "/get")
    public ResponseEntity<List<RESPONSE_DTO>> getAll() {
        logger.info("Request to get all property listings");

        List<RESPONSE_DTO> list = getService().getAll();

        logger.info("Successfully retrieved {} property listings", list.size());
        return ResponseEntity.ok(list);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<RESPONSE_DTO> update(@PathVariable UUID id, @Valid @RequestBody CREATE_DTO dto) {
        logger.info("Request to update property listing with id: {} and payload: {}", id, dto);

        RESPONSE_DTO updated = getService().update(id, dto);

        logger.info("Successfully updated property listing with id: {}", id);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/patch/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<RESPONSE_DTO> patch(@PathVariable UUID id, @Valid @RequestBody PATCH_DTO dto) {
        logger.info("Request to patch property listing with id: {} and payload: {}", id, dto);

        RESPONSE_DTO updated = getService().patch(id, dto);

        logger.info("Successfully patched property listing with id: {}", id);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/remove/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        logger.info("Request to delete property listing with id: {}", id);

        getService().deleteById(id);

        logger.info("Successfully deleted property listing with id: {}", id);
        return ResponseEntity.noContent().build();
    }
}