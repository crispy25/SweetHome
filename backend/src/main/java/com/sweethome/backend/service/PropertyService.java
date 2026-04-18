package com.sweethome.backend.service;

import com.sweethome.backend.constants.PropertySortByType;
import com.sweethome.backend.entity.Property;
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

    public Page<PropertyResponseDto> search(PropertySearchDto req) {
        final Pageable pageable = toPageable(req);
        final Specification<Property> spec = PropertySpecification.toSpecification(req);
        final Page<Property> page = propertyRepository.findAll(spec, pageable);
        return page.map(propertyMapper::toDto);
    }

    public void deleteById(UUID id) {
        propertyRepository.deleteById(id);
    }

    private Pageable toPageable(PropertySearchDto req) {
        final int page = req.getPage() != null ? req.getPage() : 0;
        final int size = req.getSize() != null ? req.getSize() : DEFAULT_PAGE_SIZE;
        final String sortBy = req.getSortBy() != null ? req.getSortBy().getField() : PropertySortByType.PRICE.getField();
        final Sort.Direction direction = req.getSortDirection() != null ? req.getSortDirection() : Sort.Direction.ASC;
        return PageRequest.of(page, size, Sort.by(direction, sortBy));
    }
}
