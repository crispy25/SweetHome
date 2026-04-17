package com.sweethome.backend.service;

import com.sweethome.backend.entity.Favorite;
import com.sweethome.backend.entity.FavoriteId;
import com.sweethome.backend.entity.Property;
import com.sweethome.backend.entity.User;
import com.sweethome.backend.exception.ResourceNotFoundException;
import com.sweethome.backend.repository.FavoriteRepository;
import com.sweethome.backend.repository.PropertyPhotoRepository;
import com.sweethome.backend.repository.PropertyRepository;
import com.sweethome.backend.service.dto.FavoriteResponseDto;
import com.sweethome.backend.service.dto.PropertyResponseDto;
import com.sweethome.backend.service.mapper.FavoriteMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final PropertyRepository propertyRepository;
    private final PropertyPhotoRepository photoRepository;
    private final UserService userService;
    private final FavoriteMapper favoriteMapper;

    public FavoriteService(FavoriteRepository favoriteRepository, PropertyRepository propertyRepository, PropertyPhotoRepository photoRepository, UserService userService, FavoriteMapper favoriteMapper) {
        this.favoriteRepository = favoriteRepository;
        this.propertyRepository = propertyRepository;
        this.photoRepository = photoRepository;
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

    public List<FavoriteResponseDto> getAll() {
        return favoriteRepository.findByUserId(userService.getCurrentUserId())
                .stream()
                .map(favorite -> {
                    FavoriteResponseDto dto = favoriteMapper.toDto(favorite);

                    PropertyResponseDto property = dto.getProperty();
                    property.setPhotoUrls(getPhotos(property.getId()));

                    return dto;
                })
                .toList();
    }

    private List<String> getPhotos(UUID propertyId) {
        return photoRepository.findByIdPropertyId(propertyId).stream()
                .map(photo -> photo.getId().getPhotoUrl())
                .toList();
    }
}
