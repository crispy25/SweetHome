package com.sweethome.backend.service.dto;

import com.sweethome.backend.entity.HouseListing;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class HouseListingResponseDto extends PropertyListingResponseDto {
    private Integer floors;
    private BigDecimal landArea;
    private HouseListing.HouseType houseType;

    public HouseListingResponseDto setFloors(Integer floors) {
        this.floors = floors;
        return this;
    }

    public HouseListingResponseDto setLandArea(BigDecimal landArea) {
        this.landArea = landArea;
        return this;
    }

    public HouseListingResponseDto setHouseType(HouseListing.HouseType houseType) {
        this.houseType = houseType;
        return this;
    }
}
