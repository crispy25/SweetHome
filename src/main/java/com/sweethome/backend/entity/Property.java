package com.sweethome.backend.entity;

import com.sweethome.backend.constants.PropertyType;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "properties", schema = "project")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Property {

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

    @Enumerated(EnumType.STRING)
    @Column(name = "property_type")
    private PropertyType propertyType;

    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "last_updated")
    private Instant lastUpdated;
}
