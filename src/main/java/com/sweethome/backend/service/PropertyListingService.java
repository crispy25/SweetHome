package com.sweethome.backend.service;

import com.sweethome.backend.entity.PropertyListing;
import com.sweethome.backend.entity.PropertyPhoto;
import com.sweethome.backend.entity.PropertyPhotoId;
import com.sweethome.backend.entity.User;
import com.sweethome.backend.repository.PropertyPhotoRepository;
import com.sweethome.backend.repository.UserRepository;
import com.sweethome.backend.service.dto.PropertyListingDto;
import com.sweethome.backend.service.dto.PropertyListingPatchDto;
import com.sweethome.backend.service.dto.PropertyListingResponseDto;
import com.sweethome.backend.service.mapper.PropertyListingMapper;
import com.sweethome.backend.service.mapper.UserMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;


public abstract class PropertyListingService<ENTITY extends PropertyListing,
                                            CREATE_DTO extends PropertyListingDto,
                                            PATCH_DTO extends PropertyListingPatchDto,
                                            RESPONSE_DTO extends PropertyListingResponseDto> {
    protected abstract JpaRepository<ENTITY, UUID> getRepository();
    protected abstract PropertyListingMapper<ENTITY, CREATE_DTO, PATCH_DTO, RESPONSE_DTO> getMapper();

    protected final UserRepository userRepository;
    protected final PropertyPhotoRepository photoRepository;
    protected final UserMapper userMapper;

    protected PropertyListingService(UserRepository userRepository, PropertyPhotoRepository photoRepository,  UserMapper userMapper) {
        this.userRepository = userRepository;
        this.photoRepository = photoRepository;
        this.userMapper = userMapper;
    }

    public RESPONSE_DTO save(CREATE_DTO dto) {
        final UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final String email = user.getUsername();
        final User seller = userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        final ENTITY entity = getMapper().toEntity(dto);

        entity.setSeller(seller);

        final ENTITY saved = getRepository().save(entity);
        final UUID id = saved.getId();

        savePhotos(dto, id);

        final RESPONSE_DTO responseDto = getMapper().toDto(saved);
        responseDto.setPhotoUrls(getPhotos(saved.getId()));

        return responseDto;
    }

    public RESPONSE_DTO getById(UUID id) {
        final ENTITY entity = getRepository().findById(id).orElseThrow(() -> new RuntimeException("Property not found"));
        RESPONSE_DTO dto = getMapper().toDto(entity);
        dto.setPhotoUrls(getPhotos(id));
        return dto;
    }

    public List<RESPONSE_DTO> getAll() {
        final List<ENTITY> entities = getRepository().findAll();
        return entities.stream().map(entity -> {
            RESPONSE_DTO dto = getMapper().toDto(entity);
            dto.setPhotoUrls(getPhotos(entity.getId()));
            return dto;
        }).toList();
    }

    public RESPONSE_DTO update(UUID id, CREATE_DTO dto) {
        final ENTITY entity = getRepository().findById(id).orElseThrow(() -> new RuntimeException("Property not found"));

        getMapper().updateEntity(entity, dto);
        photoRepository.deleteAll(photoRepository.findByIdPropertyId(id));
        savePhotos(dto, id);

        final RESPONSE_DTO responseDto = getMapper().toDto(entity);
        responseDto.setPhotoUrls(getPhotos(entity.getId()));

        return responseDto;
    }

    public void deleteById(UUID id) {
        photoRepository.deleteAll(photoRepository.findByIdPropertyId(id));
        getRepository().deleteById(id);
    }

    private void savePhotos(CREATE_DTO dto, UUID propertyId) {
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

    private List<String> getPhotos(UUID propertyId) {
        return photoRepository.findByIdPropertyId(propertyId).stream()
                .map(photo -> photo.getId().getPhotoUrl())
                .toList();
    }

}
