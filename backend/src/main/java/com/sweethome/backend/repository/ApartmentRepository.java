package com.sweethome.backend.repository;

import com.sweethome.backend.entity.Apartment;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ApartmentRepository extends JpaRepository<Apartment, UUID>  {
    @NonNull List<Apartment> findAll();

    @Override
    @NonNull Optional<Apartment> findById(@NonNull UUID uuid);
}
