package com.sweethome.backend.service.dto;

import com.sweethome.backend.constants.ApartmentLayout;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApartmentPatchDto extends PropertyPatchDto {

    @Schema(example = "1")
    @Min(value = 1, message = "Comfort must be at least 1")
    @Max(value = 4, message = "Comfort must be at most 4")
    private Integer comfort;
    @Schema(example = "2")
    private Integer floor;
    @Schema(example = "SEMI_DETACHED")
    private ApartmentLayout layout;
}
