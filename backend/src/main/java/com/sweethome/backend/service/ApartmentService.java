package com.sweethome.backend.service;

import com.sweethome.backend.entity.Apartment;
import com.sweethome.backend.entity.User;
import com.sweethome.backend.exception.ResourceNotFoundException;
import com.sweethome.backend.repository.ApartmentRepository;
import com.sweethome.backend.service.dto.ApartmentDto;
import com.sweethome.backend.service.dto.ApartmentPatchDto;
import com.sweethome.backend.service.dto.ApartmentResponseDto;
import com.sweethome.backend.service.mapper.ApartmentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.UUID;

@Service
@Transactional
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final UserService userService;
    private final ApartmentMapper apartmentMapper;

    public ApartmentService(ApartmentRepository apartmentRepository, UserService userService, ApartmentMapper apartmentMapper) {
        this.apartmentRepository = apartmentRepository;
        this.userService = userService;
        this.apartmentMapper = apartmentMapper;
    }

    public ApartmentResponseDto save(ApartmentDto dto) {
        final User seller = userService.getCurrentUser();

        final Apartment entity = apartmentMapper.toEntity(dto);
        entity.setSeller(seller);

        final Apartment saved = apartmentRepository.saveAndFlush(entity);

        return apartmentMapper.toDto(saved);
    }

    public ApartmentResponseDto update(UUID id, ApartmentDto dto) {
        final Apartment entity = apartmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Apartment",  "id", id));

        apartmentMapper.updateEntity(entity, dto);
        entity.setPhotoUrls(new HashSet<>(dto.getPhotoUrls()));

        final Apartment saved = apartmentRepository.saveAndFlush(entity);

        return apartmentMapper.toDto(saved);
    }

    public ApartmentResponseDto patch(UUID id, ApartmentPatchDto dto) {
        final Apartment entity = apartmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Apartment",  "id", id));

        apartmentMapper.patchEntity(entity, dto);
        if (dto.getPhotoUrls() != null)
            entity.setPhotoUrls(new HashSet<>(dto.getPhotoUrls()));

        final Apartment saved = apartmentRepository.saveAndFlush(entity);

        return apartmentMapper.toDto(saved);
    }
}
