package com.sweethome.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Embeddable
public class FavoriteId implements Serializable {
    private static final long serialVersionUID = -3629430417531954942L;
    @NotNull
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @NotNull
    @Column(name = "property_id", nullable = false)
    private UUID propertyId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FavoriteId entity = (FavoriteId) o;
        return Objects.equals(this.userId, entity.userId) &&
                Objects.equals(this.propertyId, entity.propertyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, propertyId);
    }
}