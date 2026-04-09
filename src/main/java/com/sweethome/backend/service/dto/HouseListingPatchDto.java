package com.sweethome.backend.service.dto;

import com.sweethome.backend.entity.HouseListing;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Getter
@Component
public class HouseListingPatchDto extends PropertyListingPatchDto {

    @Schema(example = "2")
    @Positive(message = "Floors must be greater than 0")
    private Integer floors;
    @Schema(example = "500.0")
    @Positive(message = "Land area must be greater than 0")
    private BigDecimal landArea;
    @Schema(example = "DETACHED")
    private HouseListing.HouseType houseType;
}
