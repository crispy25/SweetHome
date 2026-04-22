package com.sweethome.backend.repository;

import com.sweethome.backend.entity.Favorite;
import com.sweethome.backend.entity.FavoriteId;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {
    @NotNull
    Page<Favorite> findAllByUserId(@NotNull UUID userId, @NotNull Pageable pageable);

    boolean existsByUserIdAndPropertyId(@NotNull UUID userId, @NotNull UUID propertyId);
}
