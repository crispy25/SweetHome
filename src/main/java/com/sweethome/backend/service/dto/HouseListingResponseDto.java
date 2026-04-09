package com.sweethome.backend.service.dto;

import com.sweethome.backend.entity.HouseListing;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Setter
@Component
public class HouseListingResponseDto extends PropertyListingResponseDto {

    private Integer floors;
    private BigDecimal landArea;
    private HouseListing.HouseType houseType;
}
