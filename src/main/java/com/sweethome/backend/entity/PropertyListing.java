package com.sweethome.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import org.hibernate.annotations.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class PropertyListing {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Size(max = 255)
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Size(max = 255)
    @NotNull
    @Column(name = "address", nullable = false)
    private String address;

    @NotNull
    @Column(name = "rooms", nullable = false)
    private Integer rooms;

    @NotNull
    @Column(name = "bathrooms", nullable = false)
    private Integer bathrooms;

    @NotNull
    @Column(name = "usable_area", nullable = false, precision = 6, scale = 2)
    private BigDecimal usableArea;

    @NotNull
    @Column(name = "construction_year", nullable = false)
    private Integer constructionYear;

    @NotNull
    @Column(name = "price", nullable = false, precision = 9, scale = 2)
    private BigDecimal price;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "last_updated")
    private Instant lastUpdated;

    public PropertyListing setTitle(String title) {
        this.title = title;
        return this;
    }

    public PropertyListing setDescription(String description) {
        this.description = description;
        return this;
    }

    public PropertyListing setAddress(String address) {
        this.address = address;
        return this;
    }

    public PropertyListing setRooms(Integer rooms) {
        this.rooms = rooms;
        return this;
    }

    public PropertyListing setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
        return this;
    }

    public PropertyListing setUsableArea(BigDecimal usableArea) {
        this.usableArea = usableArea;
        return this;
    }

    public PropertyListing setConstructionYear(Integer constructionYear) {
        this.constructionYear = constructionYear;
        return this;
    }

    public PropertyListing setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public PropertyListing setSeller(User seller) {
        this.seller = seller;
        return this;
    }
}
