package com.sweethome.backend.service.dto;

import com.sweethome.backend.entity.HouseListing;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Getter
@Component
public class HouseListingPatchDto extends PropertyListingPatchDto {

    @Positive(message = "Floors must be greater than 0")
    private Integer floors;
    @Positive(message = "Land area must be greater than 0")
    private BigDecimal landArea;
    private HouseListing.HouseType houseType;
}
