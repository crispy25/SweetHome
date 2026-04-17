package com.sweethome.backend.service.mapper;

import com.sweethome.backend.entity.Favorite;
import com.sweethome.backend.service.dto.FavoriteResponseDto;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PropertyMapper.class)
public abstract class FavoriteMapper {

    public abstract FavoriteResponseDto toDto(@NotNull Favorite entity);
}
