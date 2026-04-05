package com.sweethome.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "house_listing", schema = "project")
public class HouseListing extends PropertyListing {
    public enum HouseType {
        DETACHED,
        DUPLEX,
        TRIPLEX,
        TERRACED,
        OTHER;
    }

    @NotNull
    @Positive
    @Column(name = "floors", nullable = false)
    private Integer floors;
    @NotNull
    @Positive
    @Column(name = "land_area", nullable = false, precision = 8, scale = 2)
    private BigDecimal landArea;
    @Enumerated(EnumType.STRING)
    @Column(name = "house_type", columnDefinition = "house_type not null")
    private HouseType houseType;

    public HouseListing setFloors(Integer floors) {
        this.floors = floors;
        return this;
    }

    public HouseListing setLandArea(BigDecimal landArea) {
        this.landArea = landArea;
        return this;
    }

    public HouseListing setHouseType(HouseType houseType) {
        this.houseType = houseType;
        return this;
    }
}