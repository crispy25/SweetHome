package com.sweethome.backend.service.dto;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Component
public abstract class PropertyListingDto {

    private String title;
    private String description;
    private String address;
    private Integer rooms;
    private Integer bathrooms;
    private BigDecimal usableArea;
    private Integer constructionYear;
    private BigDecimal price;
    private List<String> photoUrls;
    private Instant createdAt;
    private Instant lastUpdated;

    public PropertyListingDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public PropertyListingDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public PropertyListingDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public PropertyListingDto setRooms(Integer rooms) {
        this.rooms = rooms;
        return this;
    }

    public PropertyListingDto setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
        return this;
    }

    public PropertyListingDto setUsableArea(BigDecimal usableArea) {
        this.usableArea = usableArea;
        return this;
    }

    public PropertyListingDto setConstructionYear(Integer constructionYear) {
        this.constructionYear = constructionYear;
        return this;
    }

    public PropertyListingDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public PropertyListingDto setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
        return this;
    }

    public PropertyListingDto setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public PropertyListingDto setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }
}
