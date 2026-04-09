package com.sweethome.backend.service.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Setter
public abstract class PropertyListingResponseDto {

    @Getter
    private UUID id;
    private String title;
    private String description;
    private String address;
    private Integer rooms;
    private Integer bathrooms;
    private BigDecimal usableArea;
    private Integer constructionYear;
    private BigDecimal price;
    private List<String> photoUrls;
    private UserDto seller;
    private Instant createdAt;
    private Instant lastUpdated;
}
