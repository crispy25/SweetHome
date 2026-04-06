package com.sweethome.backend.service.dto;

import com.sweethome.backend.entity.ApartmentListing;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApartmentListingDto extends PropertyListingDto {

    @NotNull(message = "Comfort must not be null")
    @Min(value = 1, message = "Comfort must be at least 1")
    @Max(value = 4, message = "Comfort must be at most 4")
    private Integer comfort;
    @NotNull(message = "Floor must not be null")
    private Integer floor;
    @NotNull(message = "Apartment layout must not be null")
    private ApartmentListing.ApartmentLayout layout;
}
