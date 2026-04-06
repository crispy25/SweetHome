package com.sweethome.backend.repository;

import com.sweethome.backend.entity.ApartmentListing;
import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ApartmentListingRepository extends JpaRepository<ApartmentListing, UUID>  {
    @EntityGraph(attributePaths = {"seller"})
    @NonNull List<ApartmentListing> findAll();

    @EntityGraph(attributePaths = {"seller"})
    @Override
    @NonNull Optional<ApartmentListing> findById(@NonNull UUID uuid);
}
