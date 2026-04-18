package com.sweethome.backend.service;

import com.sweethome.backend.entity.Favorite;
import com.sweethome.backend.entity.FavoriteId;
import com.sweethome.backend.entity.Property;
import com.sweethome.backend.entity.User;
import com.sweethome.backend.exception.ResourceNotFoundException;
import com.sweethome.backend.repository.FavoriteRepository;
import com.sweethome.backend.repository.PropertyRepository;
import com.sweethome.backend.service.dto.FavoriteResponseDto;
import com.sweethome.backend.service.mapper.FavoriteMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final PropertyRepository propertyRepository;
    private final UserService userService;
    private final FavoriteMapper favoriteMapper;

    public FavoriteService(FavoriteRepository favoriteRepository, PropertyRepository propertyRepository, UserService userService, FavoriteMapper favoriteMapper) {
        this.favoriteRepository = favoriteRepository;
        this.propertyRepository = propertyRepository;
        this.userService = userService;
        this.favoriteMapper = favoriteMapper;
    }

    public void add(UUID propertyId) {
        final User user = userService.getCurrentUser();
        final UUID userId = user.getId();
        final FavoriteId id = new FavoriteId(userId, propertyId);

        if (favoriteRepository.existsById(id))
            return;

        final Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new ResourceNotFoundException("Property", "id", propertyId));

        final Favorite favorite = new Favorite();
        favorite.setId(id);
        favorite.setProperty(property);
        favorite.setUser(user);

        favoriteRepository.save(favorite);
    }

    public void remove(UUID propertyId) {
        final UUID userId = userService.getCurrentUserId();
        final FavoriteId id = new FavoriteId(userId, propertyId);

        if (!favoriteRepository.existsById(id))
            return;

        favoriteRepository.deleteById(id);
    }

    public Page<FavoriteResponseDto> getAll(int page, int size) {
        final Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        final Page<Favorite> favorites = favoriteRepository.findAllByUserId(userService.getCurrentUserId(), pageable);
        return favorites.map(favoriteMapper::toDto);
    }
}
