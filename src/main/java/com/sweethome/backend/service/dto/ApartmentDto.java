package com.sweethome.backend.service.dto;

import com.sweethome.backend.constants.ApartmentLayout;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApartmentDto extends PropertyDto {

    @Schema(example = "1")
    @NotNull(message = "Comfort must not be null")
    @Min(value = 1, message = "Comfort must be at least 1")
    @Max(value = 4, message = "Comfort must be at most 4")
    private Integer comfort;
    @Schema(example = "2")
    @NotNull(message = "Floor must not be null")
    private Integer floor;
    @Schema(example = "SEMI_DETACHED")
    @NotNull(message = "Apartment layout must not be null")
    private ApartmentLayout layout;
}
