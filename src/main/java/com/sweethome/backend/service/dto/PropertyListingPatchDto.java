package com.sweethome.backend.service.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public abstract class PropertyListingPatchDto {

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String address;
    @Positive(message = "Rooms must be greater than 0")
    private Integer rooms;
    @PositiveOrZero(message = "Bathrooms must be 0 or greater")
    private Integer bathrooms;
    @Positive(message = "Usable area must be greater than 0")
    private BigDecimal usableArea;
    @Min(value = 1500, message = "Construction year must be at least 1500")
    @Max(value = 2100, message = "Construction year must be at most 2100")
    private Integer constructionYear;
    @Positive(message = "Price must be greater than 0")
    private BigDecimal price;
    @NotNull(message = "Photo urls must not be null")
    private List<String> photoUrls;
}
