package com.sweethome.backend.repository;

import com.sweethome.backend.entity.Property;
import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PropertyRepository extends JpaRepository<Property, UUID> {
    @EntityGraph(attributePaths = {"seller"})
    @NonNull
    List<Property> findAll();

    @EntityGraph(attributePaths = {"seller"})
    @Override
    @NonNull
    Optional<Property> findById(@NonNull UUID uuid);
}
