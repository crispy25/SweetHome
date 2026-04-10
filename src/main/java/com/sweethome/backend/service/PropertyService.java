package com.sweethome.backend.service;

import com.sweethome.backend.entity.*;
import com.sweethome.backend.repository.PropertyPhotoRepository;
import com.sweethome.backend.repository.PropertyRepository;
import com.sweethome.backend.service.dto.PropertyResponseDto;
import com.sweethome.backend.service.mapper.PropertyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PropertyService extends AbstractPropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;

    public PropertyService(PropertyRepository propertyRepository, PropertyPhotoRepository photoRepository, PropertyMapper propertyMapper) {
        super(photoRepository);

        this.propertyRepository = propertyRepository;
        this.propertyMapper = propertyMapper;
    }

    public PropertyResponseDto getById(UUID id) {
        final Property entity = propertyRepository.findById(id).orElseThrow(() -> new RuntimeException("Property not found"));
        PropertyResponseDto dto = propertyMapper.toDto(entity);
        dto.setPhotoUrls(getPhotos(id));
        return dto;
    }

    public List<PropertyResponseDto> getAll() {
        final List<Property> entities = propertyRepository.findAll();
        return entities.stream().map(entity -> {
            PropertyResponseDto dto = propertyMapper.toDto(entity);
            dto.setPhotoUrls(getPhotos(entity.getId()));
            return dto;
        }).toList();
    }

    public void deleteById(UUID id) {
        photoRepository.deleteAll(photoRepository.findByIdPropertyId(id));
        propertyRepository.deleteById(id);
    }
}
