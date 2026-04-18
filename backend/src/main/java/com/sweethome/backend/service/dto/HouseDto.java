package com.sweethome.backend.service.dto;

import com.sweethome.backend.constants.HouseType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class HouseDto extends PropertyDto {

    @Schema(example = "2")
    @NotNull
    @Positive
    private Integer floors;
    @Schema(example = "500.0")
    @NotNull
    @Positive
    private BigDecimal landArea;
    @Schema(example = "DETACHED")
    @NotNull
    private HouseType houseType;
}
