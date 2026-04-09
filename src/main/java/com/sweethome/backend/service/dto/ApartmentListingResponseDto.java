package com.sweethome.backend.service.dto;

import com.sweethome.backend.entity.ApartmentListing;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Component
public class ApartmentListingResponseDto extends PropertyListingResponseDto {

    private Integer comfort;
    private Integer floor;
    private ApartmentListing.ApartmentLayout layout;
}
