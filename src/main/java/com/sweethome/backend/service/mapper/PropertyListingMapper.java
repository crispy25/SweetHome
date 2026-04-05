package com.sweethome.backend.service.mapper;

import com.sweethome.backend.entity.PropertyListing;
import com.sweethome.backend.service.dto.PropertyListingDto;
import jakarta.validation.constraints.NotNull;

public abstract class PropertyListingMapper<ENTITY extends PropertyListing, DTO extends PropertyListingDto> {
    public abstract DTO toDto(@NotNull ENTITY entity);

    public abstract ENTITY toEntity(@NotNull DTO dto);

    public abstract ENTITY updateEntity(@NotNull ENTITY entity, @NotNull DTO dto);

    protected void mapEntityToDto(PropertyListing entity, PropertyListingDto dto) {
        dto.setTitle(entity.getTitle())
                .setDescription(entity.getDescription())
                .setAddress(entity.getAddress())
                .setRooms(entity.getRooms())
                .setBathrooms(entity.getBathrooms())
                .setConstructionYear(entity.getConstructionYear())
                .setUsableArea(entity.getUsableArea())
                .setPrice(entity.getPrice())
                .setCreatedAt(entity.getCreatedAt())
                .setLastUpdated(entity.getLastUpdated());
    }

    protected void mapDtoToEntity(PropertyListingDto dto, PropertyListing entity) {
        entity.setTitle(dto.getTitle())
                .setDescription(dto.getDescription())
                .setAddress(dto.getAddress())
                .setRooms(dto.getRooms())
                .setBathrooms(dto.getBathrooms())
                .setConstructionYear(dto.getConstructionYear())
                .setUsableArea(dto.getUsableArea())
                .setPrice(dto.getPrice())
                .setCreatedAt(dto.getCreatedAt())
                .setLastUpdated(dto.getLastUpdated());
    }
}
