package com.sweethome.backend.repository;

import com.sweethome.backend.entity.House;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HouseRepository extends JpaRepository<House, UUID> {
    @NonNull List<House> findAll();

    @Override
    @NonNull Optional<House> findById(@NonNull UUID uuid);
}
