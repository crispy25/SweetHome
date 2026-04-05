package com.sweethome.backend.service;

import com.sweethome.backend.entity.PropertyListing;
import com.sweethome.backend.entity.PropertyPhoto;
import com.sweethome.backend.entity.PropertyPhotoId;
import com.sweethome.backend.entity.User;
import com.sweethome.backend.repository.PropertyPhotoRepository;
import com.sweethome.backend.repository.UserRepository;
import com.sweethome.backend.service.dto.PropertyListingDto;
import com.sweethome.backend.service.mapper.PropertyListingMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;


public abstract class PropertyListingService<ENTITY extends PropertyListing, DTO extends PropertyListingDto> {
    protected abstract JpaRepository<ENTITY, UUID> getRepository();

    protected abstract PropertyListingMapper<ENTITY, DTO> getMapper();

    protected final UserRepository userRepository;
    protected final PropertyPhotoRepository photoRepository;

    protected PropertyListingService(UserRepository userRepository, PropertyPhotoRepository photoRepository) {
        this.userRepository = userRepository;
        this.photoRepository = photoRepository;
    }

    public DTO save(DTO dto) {
        final UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final String email = user.getUsername();
        final User seller = userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        final ENTITY entity = getMapper().toEntity(dto);

        entity.setSeller(seller);

        final ENTITY saved = getRepository().save(entity);
        final UUID id = saved.getId();

        savePhotos(dto, id);

        return getMapper().toDto(saved);
    }

    public DTO getById(UUID id) {
        final ENTITY entity = getRepository().findById(id).orElseThrow(() -> new RuntimeException("Property not found"));
        final DTO dto = getMapper().toDto(entity);
        getPhotos(dto, id);
        return dto;
    }

    public List<DTO> getAll() {
        final List<ENTITY> entities = getRepository().findAll();
        return entities.stream().map(entity -> {
            DTO dto = getMapper().toDto(entity);
            getPhotos(dto, entity.getId());
            return dto;
        }).toList();
    }

    public DTO update(UUID id, DTO dto) {
        final ENTITY entity = getRepository().findById(id).orElseThrow(() -> new RuntimeException("Property not found"));

        getMapper().updateEntity(entity, dto);
        final ENTITY updated = getRepository().save(entity);
        photoRepository.deleteAll(photoRepository.findByIdPropertyId(id));
        savePhotos(dto, id);

        return getMapper().toDto(updated);
    }

    public void deleteById(UUID id) {
        photoRepository.deleteAll(photoRepository.findByIdPropertyId(id));
        getRepository().deleteById(id);
    }

    private void savePhotos(DTO dto, UUID propertyId) {
        final List<String> photoUrls = dto.getPhotoUrls();
        if (photoUrls != null && !photoUrls.isEmpty()) {
            final List<PropertyPhoto> photos = dto.getPhotoUrls().stream()
                    .map(url -> {
                        PropertyPhoto p = new PropertyPhoto();
                        p.setId(new PropertyPhotoId(propertyId, url));
                        return p;
                    })
                    .toList();
            photoRepository.saveAll(photos);
        }
    }

    private void getPhotos(DTO dto, UUID propertyId) {
        final List<String> photoUrls = photoRepository.findByIdPropertyId(propertyId).stream()
                .map(photo -> photo.getId().getPhotoUrl())
                .toList();
        dto.setPhotoUrls(photoUrls);
    }

}
