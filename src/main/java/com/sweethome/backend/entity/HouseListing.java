package com.sweethome.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "house_listing", schema = "project")
public class HouseListing extends PropertyListing {
    @Getter
    public enum HouseType {
        DETACHED("Detached"),
        DUPLEX("Duplex"),
        TRIPLEX("Triplex"),
        TERRACED("Terraced"),
        OTHER("Other");

        private final String dbValue;

        HouseType(String dbValue) {
            this.dbValue = dbValue;
        }
    }

    @NotNull
    @Column(name = "floors", nullable = false)
    private Integer floors;
    @NotNull
    @Column(name = "land_area", nullable = false, precision = 8, scale = 2)
    private BigDecimal landArea;
    @Column(name = "house_type", columnDefinition = "house_type not null")
    private HouseType houseType;
}