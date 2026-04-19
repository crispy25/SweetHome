package com.sweethome.backend.entity;

import com.sweethome.backend.enums.ApartmentLayout;
import com.sweethome.backend.enums.PropertyType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "apartments", schema = "project")
@PrimaryKeyJoinColumn(name = "id")
public class Apartment extends Property {

    public Apartment() {
        setPropertyType(PropertyType.APARTMENT);
    }

    @NotNull(message = "Comfort must not be null")
    @Min(value = 1, message = "Comfort must be at least 1")
    @Max(value = 4, message = "Comfort must be at most 4")
    @Column(name = "comfort", nullable = false)
    private Integer comfort;
    @NotNull(message = "Floor must not be null")
    @Column(name = "floor", nullable = false)
    private Integer floor;
    @Enumerated(EnumType.STRING)
    @Column(name = "layout", columnDefinition = "apartment_layout not null")
    private ApartmentLayout layout;
}