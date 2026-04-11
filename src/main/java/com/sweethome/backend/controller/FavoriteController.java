package com.sweethome.backend.controller;

import com.sweethome.backend.repository.FavoriteRepository;
import com.sweethome.backend.service.FavoriteService;
import com.sweethome.backend.service.UserService;
import com.sweethome.backend.service.dto.FavoriteResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/favorites")
public class FavoriteController implements SecuredRestController {

    private final FavoriteService favoriteService;

    private static final Logger logger = LoggerFactory.getLogger(FavoriteController.class);

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping("/add/{propertyId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Void> addFavorite(@PathVariable UUID propertyId) {
        logger.info("Request to add property {} to favorites", propertyId);

        favoriteService.add(propertyId);

        logger.info("Successfully added property {} to favorites", propertyId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remove/{propertyId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Void> removeFavorite(@PathVariable UUID propertyId) {
        logger.info("Request to remove property {} from favorites", propertyId);

        favoriteService.remove(propertyId);

        logger.info("Successfully removed property {} from favorites", propertyId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<List<FavoriteResponseDto>> getAll() {
        logger.info("Request to get all favorites");

        final List<FavoriteResponseDto> favorites = favoriteService.getAll();

        logger.info("Successfully retrieved all favorites");
        return ResponseEntity.ok(favorites);
    }
}
