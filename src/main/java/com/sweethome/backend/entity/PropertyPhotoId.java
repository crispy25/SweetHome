package com.sweethome.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PropertyPhotoId implements Serializable {
    private static final long serialVersionUID = -6247220683104392622L;
    @NotNull
    @Column(name = "property_id", nullable = false)
    private UUID propertyId;

    @NotNull
    @Column(name = "photo_url", nullable = false, length = Integer.MAX_VALUE)
    private String photoUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PropertyPhotoId entity = (PropertyPhotoId) o;
        return Objects.equals(this.photoUrl, entity.photoUrl) &&
                Objects.equals(this.propertyId, entity.propertyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photoUrl, propertyId);
    }
}