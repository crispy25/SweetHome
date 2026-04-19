package com.sweethome.backend.service.dto;

import com.sweethome.backend.enums.HouseType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class HousePatchDto extends PropertyPatchDto {

    @Schema(example = "2")
    @Positive
    private Integer floors;
    @Schema(example = "500.0")
    @Positive
    private BigDecimal landArea;
    @Schema(example = "DETACHED")
    private HouseType houseType;
}
