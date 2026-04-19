package com.sweethome.backend.service.dto;

import com.sweethome.backend.enums.ApartmentLayout;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ApartmentDto extends PropertyDto {

    @Schema(example = "1")
    @NotNull
    @Min(value = 1)
    @Max(value = 4)
    private Integer comfort;
    @Schema(example = "2")
    @NotNull
    private Integer floor;
    @Schema(example = "SEMI_DETACHED")
    @NotNull
    private ApartmentLayout layout;
}
