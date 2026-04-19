package com.sweethome.backend.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public abstract class PropertyPatchDto {

    @Schema(example = "Property for sale!")
    @Pattern(regexp = "^\\S.*\\S$", message = "No leading or trailing white spaces")
    @Size(min = 10, max = 50)
    private String title;
    @Schema(example = "Spacious garden, balcony and parking")
    @Pattern(regexp = "^\\S.*\\S$", message = "No leading or trailing white spaces")
    @Size(min = 10, max = 500)
    private String description;
    @Schema(example = "123 Main Street, Springfield")
    @Pattern(regexp = "^\\S.*\\S$", message = "No leading or trailing white spaces")
    @Size(min = 10, max = 100)
    private String address;
    @Schema(example = "4")
    @Positive
    private Integer rooms;
    @Schema(example = "2")
    @PositiveOrZero
    private Integer bathrooms;
    @Schema(example = "100.0")
    @Positive
    private BigDecimal usableArea;
    @Schema(example = "2000.0")
    @Min(value = 1500)
    @Max(value = 2100)
    private Integer constructionYear;
    @Schema(example = "100000.0")
    @Positive
    private BigDecimal price;
    @Schema(example = "[\"GardenPhoto.png\", \"RoomPhoto.png\"]")
    private List<String> photoUrls;
}
