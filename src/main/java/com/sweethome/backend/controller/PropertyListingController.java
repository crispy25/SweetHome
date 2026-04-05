package com.sweethome.backend.controller;

import com.sweethome.backend.entity.PropertyListing;
import com.sweethome.backend.service.PropertyListingService;
import com.sweethome.backend.service.dto.PropertyListingDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public abstract class PropertyListingController<DTO extends PropertyListingDto> implements SecuredRestController {
    protected abstract PropertyListingService<? extends PropertyListing, DTO> getService();

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @PostMapping(path = "/add")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<DTO> create(@RequestBody DTO dto) {
        final DTO saved = getService().save(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<DTO> getById(@PathVariable UUID id) {
        final DTO dto = getService().getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(path = "/get")
    public ResponseEntity<List<DTO>> getAll() {
        logger.info("Get request");
        final List<DTO> list = getService().getAll();
        logger.info("Get response: " + list);
        return ResponseEntity.ok(list);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<DTO> update(@PathVariable UUID id, @RequestBody DTO dto) {
        final DTO updated = getService().update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/remove/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        getService().deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
