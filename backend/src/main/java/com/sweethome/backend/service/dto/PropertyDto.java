package com.sweethome.backend.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public abstract class PropertyDto {

    @Schema(example = "Property for sale!")
    @NotBlank
    @Pattern(regexp = "^\\S.*\\S$", message = "No leading or trailing white spaces")
    @Size(min = 10, max = 50)
    private String title;
    @Schema(example = "Spacious garden, balcony and parking")
    @NotBlank
    @Pattern(regexp = "^\\S.*\\S$", message = "No leading or trailing white spaces")
    @Size(min = 10, max = 500)
    private String description;
    @Schema(example = "123 Main Street, Springfield")
    @NotBlank
    @Pattern(regexp = "^\\S.*\\S$", message = "No leading or trailing white spaces")
    @Size(min = 10, max = 100)
    private String address;
    @Schema(example = "4")
    @NotNull
    @Positive
    private Integer rooms;
    @Schema(example = "2")
    @NotNull
    @PositiveOrZero
    private Integer bathrooms;
    @Schema(example = "100.0")
    @NotNull
    @Positive
    private BigDecimal usableArea;
    @Schema(example = "2000.0")
    @NotNull
    @Min(value = 1500)
    @Max(value = 2100)
    private Integer constructionYear;
    @Schema(example = "100000.0")
    @NotNull
    @Positive
    private BigDecimal price;
    @Schema(example = "[\"GardenPhoto.png\", \"RoomPhoto.png\"]")
    @NotNull
    private List<String> photoUrls;
}
