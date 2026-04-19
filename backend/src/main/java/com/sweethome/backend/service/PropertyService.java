package com.sweethome.backend.service;

import com.sweethome.backend.entity.Property;
import com.sweethome.backend.enums.PropertySortByType;
import com.sweethome.backend.exception.ResourceNotFoundException;
import com.sweethome.backend.repository.PropertyRepository;
import com.sweethome.backend.service.dto.PropertyResponseDto;
import com.sweethome.backend.service.dto.PropertySearchDto;
import com.sweethome.backend.service.mapper.PropertyMapper;
import com.sweethome.backend.service.search.PropertySpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PropertyService {

    private static final int DEFAULT_PAGE_SIZE = 10;

    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;

    public PropertyService(PropertyRepository propertyRepository, PropertyMapper propertyMapper) {
        this.propertyRepository = propertyRepository;
        this.propertyMapper = propertyMapper;
    }

    public PropertyResponseDto getById(UUID id) {
        final Property entity = propertyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Property", "id", id));
        return propertyMapper.toDto(entity);
    }

    public List<PropertyResponseDto> getAll() {
        final List<Property> entities = propertyRepository.findAll();
        return entities.stream().map(propertyMapper::toDto).toList();
    }

    public Page<PropertyResponseDto> getUserProperties(UUID userId, int page, int size) {
        final Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        final Page<Property> entities = propertyRepository.findBySellerId(userId, pageable);
        return entities.map(propertyMapper::toDto);
    }

    public Page<PropertyResponseDto> search(int page, int size, PropertySortByType sortBy, Sort.Direction direction, PropertySearchDto req) {
        final Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy.getField()));
        final Specification<Property> spec = PropertySpecification.toSpecification(req);
        final Page<Property> properties = propertyRepository.findAll(spec, pageable);
        return properties.map(propertyMapper::toDto);
    }

    public void deleteById(UUID id) {
        propertyRepository.deleteById(id);
    }
}
