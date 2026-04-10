package com.sweethome.backend.service.dto;

import com.sweethome.backend.constants.HouseType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Getter
@Setter
@Component
public class HouseResponseDto extends PropertyResponseDto {

    private Integer floors;
    private BigDecimal landArea;
    private HouseType houseType;
}
