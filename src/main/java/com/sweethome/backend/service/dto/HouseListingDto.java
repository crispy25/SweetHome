package com.sweethome.backend.service.dto;

import com.sweethome.backend.entity.HouseListing;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Getter
@Component
public class HouseListingDto extends PropertyListingDto {
    private Integer floors;
    private BigDecimal landArea;
    private HouseListing.HouseType houseType;

    public HouseListingDto setFloors(Integer floors) {
        this.floors = floors;
        return this;
    }

    public HouseListingDto setLandArea(BigDecimal landArea) {
        this.landArea = landArea;
        return this;
    }

    public HouseListingDto setHouseType(HouseListing.HouseType houseType) {
        this.houseType = houseType;
        return this;
    }
}
