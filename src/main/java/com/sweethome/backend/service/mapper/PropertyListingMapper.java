package com.sweethome.backend.service.mapper;

import com.sweethome.backend.entity.PropertyListing;
import com.sweethome.backend.service.dto.PropertyListingDto;
import com.sweethome.backend.service.dto.PropertyListingResponseDto;
import jakarta.validation.constraints.NotNull;

public abstract class PropertyListingMapper<ENTITY extends PropertyListing, CREATE_DTO extends PropertyListingDto, RESPONSE_DTO extends PropertyListingResponseDto> {
    public abstract RESPONSE_DTO toDto(@NotNull ENTITY entity);

    public abstract ENTITY toEntity(@NotNull CREATE_DTO dto);

    public abstract ENTITY updateEntity(@NotNull ENTITY entity, @NotNull CREATE_DTO dto);

    protected void mapEntityToDto(ENTITY entity, RESPONSE_DTO dto) {
        dto.setTitle(entity.getTitle())
                .setDescription(entity.getDescription())
                .setAddress(entity.getAddress())
                .setRooms(entity.getRooms())
                .setBathrooms(entity.getBathrooms())
                .setConstructionYear(entity.getConstructionYear())
                .setUsableArea(entity.getUsableArea())
                .setPrice(entity.getPrice())
                .setId(entity.getId())
                .setCreatedAt(entity.getCreatedAt())
                .setLastUpdated(entity.getLastUpdated());
    }

    protected void mapDtoToEntity(CREATE_DTO dto, ENTITY entity) {
        entity.setTitle(dto.getTitle())
                .setDescription(dto.getDescription())
                .setAddress(dto.getAddress())
                .setRooms(dto.getRooms())
                .setBathrooms(dto.getBathrooms())
                .setConstructionYear(dto.getConstructionYear())
                .setUsableArea(dto.getUsableArea())
                .setPrice(dto.getPrice());
    }
}
