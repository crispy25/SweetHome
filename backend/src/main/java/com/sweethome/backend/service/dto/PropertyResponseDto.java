package com.sweethome.backend.service.dto;


import com.sweethome.backend.enums.PropertyType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
public class PropertyResponseDto {

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
    private UUID sellerId;
    private PropertyType propertyType;
    private Instant createdAt;
    private Instant lastUpdated;
}
