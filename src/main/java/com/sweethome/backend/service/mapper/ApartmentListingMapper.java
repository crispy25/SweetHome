package com.sweethome.backend.service.mapper;

import com.sweethome.backend.entity.ApartmentListing;
import com.sweethome.backend.service.dto.ApartmentListingDto;
import com.sweethome.backend.service.dto.ApartmentListingResponseDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class ApartmentListingMapper extends PropertyListingMapper<ApartmentListing, ApartmentListingDto, ApartmentListingResponseDto> {

    @Override
    public ApartmentListingResponseDto toDto(@NotNull ApartmentListing entity) {
        final ApartmentListingResponseDto dto = new ApartmentListingResponseDto();
        mapEntityToDto(entity, dto);

        dto.setComfort(entity.getComfort())
                .setFloor(entity.getFloor())
                .setLayout(entity.getLayout());

        return dto;
    }

    @Override
    public ApartmentListing toEntity(@NotNull ApartmentListingDto dto) {
        final ApartmentListing entity = new ApartmentListing();
        return updateEntity(entity, dto);
    }

    @Override
    public ApartmentListing updateEntity(ApartmentListing apartmentListing, ApartmentListingDto dto) {
        mapDtoToEntity(dto, apartmentListing);

        apartmentListing.setComfort(dto.getComfort())
                .setFloor(dto.getFloor())
                .setLayout(dto.getLayout());

        return apartmentListing;
    }
}
