package com.sweethome.backend.entity;

import com.sweethome.backend.enums.HouseType;
import com.sweethome.backend.enums.PropertyType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "houses", schema = "project")
@PrimaryKeyJoinColumn(name = "id")
public class House extends Property {

    public House() {
        setPropertyType(PropertyType.HOUSE);
    }

    @NotNull
    @Column(name = "floors", nullable = false)
    private Integer floors;
    @NotNull
    @Column(name = "land_area", nullable = false, precision = 8, scale = 2)
    private BigDecimal landArea;
    @Enumerated(EnumType.STRING)
    @Column(name = "house_type", columnDefinition = "house_type not null")
    private HouseType houseType;
}