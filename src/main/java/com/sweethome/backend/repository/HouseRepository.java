package com.sweethome.backend.repository;

import com.sweethome.backend.entity.House;
import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HouseRepository extends JpaRepository<House, UUID> {
    @EntityGraph(attributePaths = {"seller"})
    @NonNull List<House> findAll();

    @EntityGraph(attributePaths = {"seller"})
    @Override
    @NonNull Optional<House> findById(@NonNull UUID uuid);
}
