package com.sweethome.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "apartment_listing", schema = "project")
public class ApartmentListing extends PropertyListing {
    public enum ApartmentLayout {
        DETACHED,
        SEMI_DETACHED,
        NON_DETACHED,
        CIRCULAR
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

    public ApartmentListing setComfort(Integer comfort) {
        this.comfort = comfort;
        return this;
    }

    public ApartmentListing setFloor(Integer floor) {
        this.floor = floor;
        return this;
    }

    public ApartmentListing setLayout(ApartmentLayout layout) {
        this.layout = layout;
        return this;
    }
}