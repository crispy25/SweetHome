package com.sweethome.backend.service.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public abstract class PropertyListingDto {

    @NotNull(message = "Title must not be null")
    @NotBlank
    private String title;
    @NotNull(message = "Description must not be null")
    @NotBlank
    private String description;
    @NotNull(message = "Address must not be null")
    @NotBlank
    private String address;
    @NotNull(message = "Rooms must not be null")
    @Positive(message = "Rooms must be greater than 0")
    private Integer rooms;
    @NotNull(message = "Bathrooms must not be null")
    @PositiveOrZero(message = "Bathrooms must be 0 or greater")
    private Integer bathrooms;
    @NotNull(message = "Usable area must not be null")
    @Positive(message = "Usable area must be greater than 0")
    private BigDecimal usableArea;
    @NotNull(message = "Construction year must not be null")
    @Min(value = 1500, message = "Construction year must be at least 1500")
    @Max(value = 2100, message = "Construction year must be at most 2100")
    private Integer constructionYear;
    @NotNull(message = "Price must not be null")
    @Positive(message = "Price must be greater than 0")
    private BigDecimal price;
    @NotNull(message = "Photo urls must not be null")
    private List<String> photoUrls;
}
