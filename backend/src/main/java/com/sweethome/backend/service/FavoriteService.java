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
    private final CurrentUserService currentUserService;
    private final FavoriteMapper favoriteMapper;

    public FavoriteService(FavoriteRepository favoriteRepository, PropertyRepository propertyRepository, CurrentUserService currentUserService, FavoriteMapper favoriteMapper) {
        this.favoriteRepository = favoriteRepository;
        this.propertyRepository = propertyRepository;
        this.currentUserService = currentUserService;
        this.favoriteMapper = favoriteMapper;
    }

    public FavoriteResponseDto add(UUID propertyId) {
        final User user = currentUserService.getCurrentUser();
        final UUID userId = user.getId();
        final FavoriteId id = new FavoriteId(userId, propertyId);

        if (favoriteRepository.existsById(id))
            return favoriteMapper.toDto(favoriteRepository.findById(id).get());

        final Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new ResourceNotFoundException("Property", "id", propertyId));

        final Favorite favorite = new Favorite();
        favorite.setId(id);
        favorite.setProperty(property);
        favorite.setUser(user);
        final Favorite saved = favoriteRepository.saveAndFlush(favorite);

        return favoriteMapper.toDto(saved);
    }

    public void remove(UUID propertyId) {
        final UUID userId = currentUserService.getCurrentUserId();
        final FavoriteId id = new FavoriteId(userId, propertyId);

        favoriteRepository.deleteById(id);
    }

    public Page<FavoriteResponseDto> getAll(int page, int size) {
        final Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        final Page<Favorite> favorites = favoriteRepository.findAllByUserId(currentUserService.getCurrentUserId(), pageable);
        return favorites.map(favoriteMapper::toDto);
    }
}
