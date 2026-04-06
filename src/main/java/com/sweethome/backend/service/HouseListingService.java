package com.sweethome.backend.service;

import com.sweethome.backend.entity.HouseListing;
import com.sweethome.backend.repository.HouseListingRepository;
import com.sweethome.backend.repository.PropertyPhotoRepository;
import com.sweethome.backend.repository.UserRepository;
import com.sweethome.backend.service.dto.HouseListingDto;
import com.sweethome.backend.service.dto.HouseListingResponseDto;
import com.sweethome.backend.service.mapper.HouseListingMapper;
import com.sweethome.backend.service.mapper.PropertyListingMapper;
import com.sweethome.backend.service.mapper.UserMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class HouseListingService extends PropertyListingService<HouseListing, HouseListingDto, HouseListingResponseDto> {
    private final HouseListingRepository houseListingRepository;
    private final HouseListingMapper houseListingMapper;

    public HouseListingService(UserRepository userRepository, PropertyPhotoRepository propertyPhotoRepository, UserMapper userMapper, HouseListingRepository houseListingRepository, HouseListingMapper houseListingMapper) {
        super(userRepository, propertyPhotoRepository, userMapper);
        this.houseListingRepository = houseListingRepository;
        this.houseListingMapper = houseListingMapper;
    }

    @Override
    protected JpaRepository<HouseListing, UUID> getRepository() {
        return houseListingRepository;
    }

    @Override
    protected PropertyListingMapper<HouseListing, HouseListingDto, HouseListingResponseDto> getMapper() {
        return houseListingMapper;
    }
}
