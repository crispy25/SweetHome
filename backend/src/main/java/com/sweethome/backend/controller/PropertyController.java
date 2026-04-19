package com.sweethome.backend.controller;

import com.sweethome.backend.enums.PropertySortByType;
import com.sweethome.backend.service.PropertyService;
import com.sweethome.backend.service.dto.PropertyResponseDto;
import com.sweethome.backend.service.dto.PropertySearchDto;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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

        PropertyResponseDto response = propertyService.getById(id);

        logger.info("Successfully retrieved property listing with id: {}", id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PropertyResponseDto>> getAll() {
        logger.info("Request to get all property listings");

        List<PropertyResponseDto> response = propertyService.getAll();

        logger.info("Successfully retrieved {} property listings", response.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user")
    public ResponseEntity<Page<PropertyResponseDto>> getUserProperties(@RequestParam() UUID userId,
                                                                       @RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "5") int size) {
        logger.info("Request to get property listings for user with id: {}", userId);

        Page<PropertyResponseDto> response = propertyService.getUserProperties(userId, page, size);

        logger.info("Successfully retrieved {} property listings for user with id: {}", response.getNumberOfElements(), userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<PropertyResponseDto>> search(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "5") int size,
                                                            @RequestParam(name = "sort_by", defaultValue = "CREATED_AT") PropertySortByType sortBy,
                                                            @RequestParam(name = "order", defaultValue = "DESC") Sort.Direction sortDir,
                                                            @RequestBody @Valid PropertySearchDto request) {
        logger.info("Request to search for property listings");

        Page<PropertyResponseDto> response = propertyService.search(page, size, sortBy, sortDir, request);

        logger.info("Successfully retrieved {} properties", response.getNumberOfElements());
        return ResponseEntity.ok(response);
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