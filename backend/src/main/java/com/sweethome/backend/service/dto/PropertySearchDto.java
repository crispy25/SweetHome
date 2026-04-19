package com.sweethome.backend.service.dto;

import com.sweethome.backend.enums.ApartmentLayout;
import com.sweethome.backend.enums.HouseType;
import com.sweethome.backend.enums.PropertyType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
public class PropertySearchDto {

    @Schema(example = "2")
    @Positive
    private Integer minRooms;
    @Schema(example = "5")
    @Positive
    private Integer maxRooms;

    @Schema(example = "1")
    @PositiveOrZero
    private Integer minBathrooms;
    @Schema(example = "3")
    @PositiveOrZero
    private Integer maxBathrooms;

    @Schema(example = "1000.0")
    @Positive
    private BigDecimal minPrice;
    @Schema(example = "100000.0")
    @Positive
    private BigDecimal maxPrice;

    @Schema(example = "50.0")
    @Positive
    private BigDecimal minUsableArea;
    @Schema(example = "100.0")
    @Positive
    private BigDecimal maxUsableArea;

    @Schema(example = "1990")
    @Min(value = 1500)
    @Max(value = 2100)
    private Integer minConstructionYear;
    @Schema(example = "2010")
    @Min(value = 1500)
    @Max(value = 2100)
    private Integer maxConstructionYear;

    private PropertyType propertyType;

    @Schema(example = "1970-01-01T00:00:00Z")
    private Instant createdAfter;
    private Instant createdBefore;

    @Schema(example = "1")
    private Integer minFloor;
    @Schema(example = "10")
    private Integer maxFloor;

    @Schema(example = "1")
    @Min(value = 1)
    @Max(value = 4)
    private Integer comfort;

    @Schema(example = "DETACHED")
    private ApartmentLayout layout;

    @Schema(example = "1")
    @Positive
    private Integer minFloors;
    @Schema(example = "3")
    @Positive
    private Integer maxFloors;

    @Schema(example = "50.0")
    @Positive
    private BigDecimal minLandArea;
    @Schema(example = "100.0")
    @Positive
    private BigDecimal maxLandArea;

    @Schema(example = "DETACHED")
    private HouseType houseType;
}
