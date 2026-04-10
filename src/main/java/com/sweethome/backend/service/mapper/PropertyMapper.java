package com.sweethome.backend.service.mapper;

import com.sweethome.backend.entity.Apartment;
import com.sweethome.backend.entity.House;
import com.sweethome.backend.entity.Property;
import com.sweethome.backend.service.dto.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class PropertyMapper {
    private final ApartmentMapper apartmentMapper;
    private final HouseMapper houseMapper;

    public PropertyMapper(ApartmentMapper apartmentMapper, HouseMapper houseMapper) {
        this.apartmentMapper = apartmentMapper;
        this.houseMapper = houseMapper;
    }

    public PropertyResponseDto toDto(@NotNull Property property) {
        if (property instanceof Apartment apartment)
            return apartmentMapper.toDto(apartment);
        else if (property instanceof House house)
            return houseMapper.toDto(house);
        throw new IllegalArgumentException("Unknown type");
    }
}
