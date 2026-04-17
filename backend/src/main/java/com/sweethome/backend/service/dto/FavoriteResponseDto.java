package com.sweethome.backend.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class FavoriteResponseDto {

    private PropertyResponseDto property;
    private Instant createdAt;
}
