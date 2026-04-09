package com.sweethome.backend.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public abstract class PropertyListingPatchDto {

    @Schema(example = "Property for sale!")
    @Pattern(regexp = "^\\S.*\\S$", message = "No leading or trailing white spaces")
    @Size(min = 10, max = 50, message = "Title must have at least 10 characters and at most 50 characters")
    private String title;
    @Schema(example = "Spacious garden, balcony and parking")
    @Pattern(regexp = "^\\S.*\\S$", message = "No leading or trailing white spaces")
    @Size(min = 10, max = 500, message = "Description must have at least 10 characters and at most 500 characters")
    private String description;
    @Schema(example = "123 Main Street, Springfield")
    @Pattern(regexp = "^\\S.*\\S$", message = "No leading or trailing white spaces")
    @Size(min = 10, max = 100, message = "Address must have at least 10 characters and at most 100 characters")
    private String address;
    @Schema(example = "4")
    @Positive(message = "Rooms must be greater than 0")
    private Integer rooms;
    @Schema(example = "2")
    @PositiveOrZero(message = "Bathrooms must be 0 or greater")
    private Integer bathrooms;
    @Schema(example = "100.0")
    @Positive(message = "Usable area must be greater than 0")
    private BigDecimal usableArea;
    @Schema(example = "2000.0")
    @Min(value = 1500, message = "Construction year must be at least 1500")
    @Max(value = 2100, message = "Construction year must be at most 2100")
    private Integer constructionYear;
    @Schema(example = "100000.0")
    @Positive(message = "Price must be greater than 0")
    private BigDecimal price;
    @Schema(example = "[\"GardenPhoto.png\", \"RoomPhoto.png\"]")
    @NotNull(message = "Photo urls must not be null")
    private List<String> photoUrls;
}
