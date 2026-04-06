package com.sweethome.backend.service;

import com.sweethome.backend.entity.ApartmentListing;
import com.sweethome.backend.repository.ApartmentListingRepository;
import com.sweethome.backend.repository.PropertyPhotoRepository;
import com.sweethome.backend.repository.UserRepository;
import com.sweethome.backend.service.dto.ApartmentListingDto;
import com.sweethome.backend.service.dto.ApartmentListingResponseDto;
import com.sweethome.backend.service.mapper.ApartmentListingMapper;
import com.sweethome.backend.service.mapper.PropertyListingMapper;
import com.sweethome.backend.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class ApartmentListingService extends PropertyListingService<ApartmentListing, ApartmentListingDto, ApartmentListingResponseDto> {
    private final ApartmentListingRepository apartmentListingRepository;
    private final ApartmentListingMapper apartmentListingMapper;

    @Autowired
    public ApartmentListingService(UserRepository userRepository, PropertyPhotoRepository propertyPhotoRepository, UserMapper userMapper, ApartmentListingRepository apartmentListingRepository, ApartmentListingMapper apartmentListingMapper) {
        super(userRepository, propertyPhotoRepository, userMapper);
        this.apartmentListingRepository = apartmentListingRepository;
        this.apartmentListingMapper = apartmentListingMapper;
    }

    @Override
    protected JpaRepository<ApartmentListing, UUID> getRepository() {
        return apartmentListingRepository;
    }

    @Override
    protected PropertyListingMapper<ApartmentListing, ApartmentListingDto, ApartmentListingResponseDto> getMapper() {
        return apartmentListingMapper;
    }
}
