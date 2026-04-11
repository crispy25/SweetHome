package com.sweethome.backend.service.dto;

import com.sweethome.backend.constants.HouseType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class HouseResponseDto extends PropertyResponseDto {

    private Integer floors;
    private BigDecimal landArea;
    private HouseType houseType;
}
