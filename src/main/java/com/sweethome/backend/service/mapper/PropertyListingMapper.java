package com.sweethome.backend.service.mapper;

import com.sweethome.backend.entity.PropertyListing;
import com.sweethome.backend.service.dto.PropertyListingDto;
import com.sweethome.backend.service.dto.PropertyListingPatchDto;
import com.sweethome.backend.service.dto.PropertyListingResponseDto;
import jakarta.validation.constraints.NotNull;

public abstract class PropertyListingMapper<ENTITY extends PropertyListing,
                                            CREATE_DTO extends PropertyListingDto,
                                            PATCH_DTO extends PropertyListingPatchDto,
                                            RESPONSE_DTO extends PropertyListingResponseDto> {

    public abstract RESPONSE_DTO toDto(@NotNull ENTITY entity);

    public abstract ENTITY toEntity(@NotNull CREATE_DTO dto);

    public abstract void updateEntity(@NotNull ENTITY entity, @NotNull CREATE_DTO dto);

    public abstract void patchEntity(@NotNull ENTITY entity, @NotNull PATCH_DTO dto);
}
