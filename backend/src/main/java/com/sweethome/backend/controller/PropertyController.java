package com.sweethome.backend.controller;

import com.sweethome.backend.service.PropertyService;
import com.sweethome.backend.service.dto.PropertyResponseDto;
import com.sweethome.backend.service.dto.PropertySearchDto;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController implements SecuredRestController {

    private final PropertyService propertyService;

    private static final Logger logger = LoggerFactory.getLogger(PropertyController.class);

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PropertyResponseDto> getById(@PathVariable UUID id) {
        logger.info("Request to get property listing with id: {}", id);

        PropertyResponseDto dto = propertyService.getById(id);

        logger.info("Successfully retrieved property listing with id: {}", id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<PropertyResponseDto>> getAll() {
        logger.info("Request to get all property listings");

        List<PropertyResponseDto> list = propertyService.getAll();

        logger.info("Successfully retrieved {} property listings", list.size());
        return ResponseEntity.ok(list);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<PropertyResponseDto>> search(@RequestBody @Valid PropertySearchDto request) {
        logger.info("Request to search for property listings");

        Page<PropertyResponseDto> page = propertyService.search(request);

        logger.info("Successfully retrieved {} properties", page.getNumberOfElements());
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@propertyAuthorization.canModifyProperty(#id)")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        logger.info("Request to delete property listing with id: {}", id);

        propertyService.deleteById(id);

        logger.info("Successfully deleted property listing with id: {}", id);
        return ResponseEntity.noContent().build();
    }
}