package com.sweethome.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "apartment_listing", schema = "project")
public class ApartmentListing extends PropertyListing {
    @Getter
    public enum ApartmentLayout {
        DETACHED("Detached"),
        DUPLEX("Semi-detached"),
        TRIPLEX("Non-detached"),
        OTHER("Circular");

        private final String dbValue;

        ApartmentLayout(String dbValue) {
            this.dbValue = dbValue;
        }
    }

    @NotNull
    @Column(name = "comfort", nullable = false)
    private Integer comfort;
    @NotNull
    @Column(name = "floor", nullable = false)
    private Integer floor;
    @Column(name = "layout", columnDefinition = "apartment_layout not null")
    private ApartmentLayout layout;
}