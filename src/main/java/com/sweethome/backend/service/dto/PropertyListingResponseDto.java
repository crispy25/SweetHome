package com.sweethome.backend.service.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Getter
public abstract class PropertyListingResponseDto {

    private String title;
    private String description;
    private String address;
    private Integer rooms;
    private Integer bathrooms;
    private BigDecimal usableArea;
    private Integer constructionYear;
    private BigDecimal price;
    private List<String> photoUrls;
    private UserDto seller;
    private UUID id;
    private Instant createdAt;
    private Instant lastUpdated;

    public PropertyListingResponseDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public PropertyListingResponseDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public PropertyListingResponseDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public PropertyListingResponseDto setRooms(Integer rooms) {
        this.rooms = rooms;
        return this;
    }

    public PropertyListingResponseDto setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
        return this;
    }

    public PropertyListingResponseDto setUsableArea(BigDecimal usableArea) {
        this.usableArea = usableArea;
        return this;
    }

    public PropertyListingResponseDto setConstructionYear(Integer constructionYear) {
        this.constructionYear = constructionYear;
        return this;
    }

    public PropertyListingResponseDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public PropertyListingResponseDto setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
        return this;
    }

    public PropertyListingResponseDto setSeller(UserDto seller) {
        this.seller = seller;
        return this;
    }

    public PropertyListingResponseDto setId(UUID id) {
        this.id = id;
        return this;
    }

    public PropertyListingResponseDto setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public PropertyListingResponseDto setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }
}
