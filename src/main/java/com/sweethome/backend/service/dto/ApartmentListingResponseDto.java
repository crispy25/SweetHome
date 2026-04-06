package com.sweethome.backend.service.dto;

import com.sweethome.backend.entity.ApartmentListing;
import org.springframework.stereotype.Component;

@Component
public class ApartmentListingResponseDto extends PropertyListingResponseDto {

    private Integer comfort;
    private Integer floor;
    private ApartmentListing.ApartmentLayout layout;

    public ApartmentListingResponseDto setComfort(Integer comfort) {
        this.comfort = comfort;
        return this;
    }

    public ApartmentListingResponseDto setFloor(Integer floor) {
        this.floor = floor;
        return this;
    }

    public ApartmentListingResponseDto setLayout(ApartmentListing.ApartmentLayout layout) {
        this.layout = layout;
        return this;
    }
}
