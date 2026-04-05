package com.sweethome.backend.service.dto;

import com.sweethome.backend.entity.ApartmentListing;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApartmentListingDto extends PropertyListingDto {

    private Integer comfort;
    private Integer floor;
    private ApartmentListing.ApartmentLayout layout;

    public ApartmentListingDto setComfort(Integer comfort) {
        this.comfort = comfort;
        return this;
    }

    public ApartmentListingDto setFloor(Integer floor) {
        this.floor = floor;
        return this;
    }

    public ApartmentListingDto setLayout(ApartmentListing.ApartmentLayout layout) {
        this.layout = layout;
        return this;
    }
}
