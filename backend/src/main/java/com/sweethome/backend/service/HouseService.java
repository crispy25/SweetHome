package com.sweethome.backend.service;

import com.sweethome.backend.entity.*;
import com.sweethome.backend.exception.ResourceNotFoundException;
import com.sweethome.backend.repository.HouseRepository;
import com.sweethome.backend.repository.PropertyPhotoRepository;
import com.sweethome.backend.repository.UserRepository;
import com.sweethome.backend.service.dto.*;
import com.sweethome.backend.service.mapper.HouseMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class HouseService extends AbstractPropertyService {

    private final HouseRepository houseRepository;
    private final UserRepository userRepository;
    private final HouseMapper houseMapper;

    public HouseService(HouseRepository houseRepository, UserRepository userRepository, PropertyPhotoRepository photoRepository, HouseMapper apartmentMapper) {
        super(photoRepository);

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
        final UUID id = saved.getId();

        savePhotos(dto.getPhotoUrls(), id);

        final HouseResponseDto responseDto = houseMapper.toDto(saved);
        responseDto.setPhotoUrls(getPhotos(saved.getId()));

        return responseDto;
    }

    public HouseResponseDto update(UUID id, HouseDto dto) {
        final House entity = houseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("House",  "id", id));

        houseMapper.updateEntity(entity, dto);

        final House saved = houseRepository.saveAndFlush(entity);

        photoRepository.deleteAll(photoRepository.findByIdPropertyId(id));
        savePhotos(dto.getPhotoUrls(), id);

        final HouseResponseDto responseDto = houseMapper.toDto(saved);
        responseDto.setPhotoUrls(getPhotos(id));

        return responseDto;
    }

    public HouseResponseDto patch(UUID id, HousePatchDto dto) {
        final House entity = houseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("House",  "id", id));

        houseMapper.patchEntity(entity, dto);

        final House saved = houseRepository.saveAndFlush(entity);

        if (dto.getPhotoUrls() != null) {
            photoRepository.deleteAll(photoRepository.findByIdPropertyId(id));
            savePhotos(dto.getPhotoUrls(), id);
        }

        final HouseResponseDto responseDto = houseMapper.toDto(saved);
        responseDto.setPhotoUrls(getPhotos(id));

        return responseDto;
    }
}
