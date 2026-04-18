package com.sweethome.backend.service;

import com.sweethome.backend.entity.House;
import com.sweethome.backend.entity.User;
import com.sweethome.backend.exception.ResourceNotFoundException;
import com.sweethome.backend.repository.HouseRepository;
import com.sweethome.backend.repository.UserRepository;
import com.sweethome.backend.service.dto.HouseDto;
import com.sweethome.backend.service.dto.HousePatchDto;
import com.sweethome.backend.service.dto.HouseResponseDto;
import com.sweethome.backend.service.mapper.HouseMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.UUID;

@Service
@Transactional
public class HouseService {

    private final HouseRepository houseRepository;
    private final UserRepository userRepository;
    private final HouseMapper houseMapper;

    public HouseService(HouseRepository houseRepository, UserRepository userRepository, HouseMapper apartmentMapper) {
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
        this.houseMapper = apartmentMapper;
    }

    public HouseResponseDto save(HouseDto dto) {
        final UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final String email = user.getUsername();
        final User seller = userRepository.findUserByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User",  "email", email));

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
