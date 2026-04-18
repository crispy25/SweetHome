package com.sweethome.backend.service;

import com.sweethome.backend.entity.House;
import com.sweethome.backend.entity.User;
import com.sweethome.backend.exception.ResourceNotFoundException;
import com.sweethome.backend.repository.HouseRepository;
import com.sweethome.backend.service.dto.HouseDto;
import com.sweethome.backend.service.dto.HousePatchDto;
import com.sweethome.backend.service.dto.HouseResponseDto;
import com.sweethome.backend.service.mapper.HouseMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.UUID;

@Service
@Transactional
public class HouseService {

    private final HouseRepository houseRepository;
    private final UserService userService;
    private final HouseMapper houseMapper;

    public HouseService(HouseRepository houseRepository, UserService userService, HouseMapper apartmentMapper) {
        this.houseRepository = houseRepository;
        this.userService = userService;
        this.houseMapper = apartmentMapper;
    }

    public HouseResponseDto save(HouseDto dto) {
        final User seller = userService.getCurrentUser();

        final House entity = houseMapper.toEntity(dto);
        entity.setSeller(seller);

        final House saved = houseRepository.saveAndFlush(entity);

        return houseMapper.toDto(saved);
    }

    public HouseResponseDto update(UUID id, HouseDto dto) {
        final House entity = houseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("House",  "id", id));

        houseMapper.updateEntity(entity, dto);
        entity.setPhotoUrls(new HashSet<>(dto.getPhotoUrls()));

        final House saved = houseRepository.saveAndFlush(entity);

        return houseMapper.toDto(saved);
    }

    public HouseResponseDto patch(UUID id, HousePatchDto dto) {
        final House entity = houseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("House",  "id", id));

        houseMapper.patchEntity(entity, dto);
        if (dto.getPhotoUrls() != null)
            entity.setPhotoUrls(new HashSet<>(dto.getPhotoUrls()));

        final House saved = houseRepository.saveAndFlush(entity);

        return houseMapper.toDto(saved);
    }
}
