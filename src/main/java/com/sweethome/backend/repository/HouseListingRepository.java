package com.sweethome.backend.repository;

import com.sweethome.backend.entity.HouseListing;
import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HouseListingRepository extends JpaRepository<HouseListing, UUID> {
    @EntityGraph(attributePaths = {"seller"})
    @NonNull List<HouseListing> findAll();

    @EntityGraph(attributePaths = {"seller"})
    @Override
    @NonNull Optional<HouseListing> findById(@NonNull UUID uuid);
}
