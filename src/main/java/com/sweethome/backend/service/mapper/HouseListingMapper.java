package com.sweethome.backend.service.mapper;

import com.sweethome.backend.entity.HouseListing;
import com.sweethome.backend.service.dto.HouseListingDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class HouseListingMapper extends PropertyListingMapper<HouseListing, HouseListingDto> {

    @Override
    public HouseListingDto toDto(@NotNull HouseListing entity) {
        final HouseListingDto dto = new HouseListingDto();
        mapEntityToDto(entity, dto);

        dto.setFloors(entity.getFloors())
                .setLandArea(entity.getLandArea())
                .setHouseType(entity.getHouseType());

        return dto;
    }

    @Override
    public HouseListing toEntity(@NotNull HouseListingDto dto) {
        final HouseListing entity = new HouseListing();
        return updateEntity(entity, dto);
    }

    @Override
    public HouseListing updateEntity(HouseListing houseListing, HouseListingDto dto) {
        mapDtoToEntity(dto, houseListing);

        houseListing.setFloors(dto.getFloors())
                .setLandArea(dto.getLandArea())
                .setHouseType(dto.getHouseType());

        return houseListing;
    }
}
