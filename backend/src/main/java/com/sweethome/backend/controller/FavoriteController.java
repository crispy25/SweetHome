package com.sweethome.backend.controller;

import com.sweethome.backend.service.FavoriteService;
import com.sweethome.backend.service.dto.FavoriteResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/favorites")
public class FavoriteController implements SecuredRestController {

    private final FavoriteService favoriteService;

    private static final Logger logger = LoggerFactory.getLogger(FavoriteController.class);

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping("/{propertyId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Void> addFavorite(@PathVariable UUID propertyId) {
        logger.info("Request to add property {} to favorites", propertyId);

        favoriteService.add(propertyId);

        logger.info("Successfully added property {} to favorites", propertyId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{propertyId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Void> removeFavorite(@PathVariable UUID propertyId) {
        logger.info("Request to remove property {} from favorites", propertyId);

        favoriteService.remove(propertyId);

        logger.info("Successfully removed property {} from favorites", propertyId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Page<FavoriteResponseDto>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        logger.info("Request to get favorites");

        final Page<FavoriteResponseDto> favorites = favoriteService.getAll(page, size);

        logger.info("Successfully retrieved favorites: {}", favorites);
        return ResponseEntity.ok(favorites);
    }
}
