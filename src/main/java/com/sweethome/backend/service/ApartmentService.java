package com.sweethome.backend.service;

import com.sweethome.backend.entity.*;
import com.sweethome.backend.repository.ApartmentRepository;
import com.sweethome.backend.repository.PropertyPhotoRepository;
import com.sweethome.backend.repository.UserRepository;
import com.sweethome.backend.service.dto.ApartmentDto;
import com.sweethome.backend.service.dto.ApartmentPatchDto;
import com.sweethome.backend.service.dto.ApartmentResponseDto;
import com.sweethome.backend.service.mapper.ApartmentMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class ApartmentService extends AbstractPropertyService {

    private final ApartmentRepository apartmentRepository;
    private final UserRepository userRepository;
    private final ApartmentMapper apartmentMapper;

    public ApartmentService(ApartmentRepository apartmentRepository, UserRepository userRepository, PropertyPhotoRepository photoRepository, ApartmentMapper apartmentMapper) {
        super(photoRepository);

        this.apartmentRepository = apartmentRepository;
        this.userRepository = userRepository;
        this.apartmentMapper = apartmentMapper;
    }

    public ApartmentResponseDto save(ApartmentDto dto) {
        final UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final String email = user.getUsername();
        final User seller = userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        final Apartment entity = apartmentMapper.toEntity(dto);

        entity.setSeller(seller);

        final Apartment saved = apartmentRepository.saveAndFlush(entity);
        final UUID id = saved.getId();

        savePhotos(dto.getPhotoUrls(), id);

        final ApartmentResponseDto responseDto = apartmentMapper.toDto(saved);
        responseDto.setPhotoUrls(getPhotos(saved.getId()));

        return responseDto;
    }

    public ApartmentResponseDto update(UUID id, ApartmentDto dto) {
        final Apartment entity = apartmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Apartment not found"));

        apartmentMapper.updateEntity(entity, dto);

        final Apartment saved = apartmentRepository.saveAndFlush(entity);

        photoRepository.deleteAll(photoRepository.findByIdPropertyId(id));
        savePhotos(dto.getPhotoUrls(), id);

        final ApartmentResponseDto responseDto = apartmentMapper.toDto(saved);
        responseDto.setPhotoUrls(getPhotos(id));

        return responseDto;
    }

    public ApartmentResponseDto patch(UUID id, ApartmentPatchDto dto) {
        final Apartment entity = apartmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Apartment not found"));

        apartmentMapper.patchEntity(entity, dto);

        final Apartment saved = apartmentRepository.saveAndFlush(entity);

        if (dto.getPhotoUrls() != null) {
            photoRepository.deleteAll(photoRepository.findByIdPropertyId(id));
            savePhotos(dto.getPhotoUrls(), id);
        }

        final ApartmentResponseDto responseDto = apartmentMapper.toDto(saved);
        responseDto.setPhotoUrls(getPhotos(id));

        return responseDto;
    }
}
