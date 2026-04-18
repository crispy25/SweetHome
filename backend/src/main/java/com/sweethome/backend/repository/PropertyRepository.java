package com.sweethome.backend.repository;

import com.sweethome.backend.entity.Property;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PropertyRepository extends JpaRepository<Property, UUID>, JpaSpecificationExecutor<Property> {
    @EntityGraph(attributePaths = {"seller"})
    @NonNull
    List<Property> findAll();

    @EntityGraph(attributePaths = {"seller"})
    @Override
    @NonNull
    Optional<Property> findById(@NonNull UUID uuid);

    @NonNull
    Page<Property> findAll(@NonNull Specification<Property> spec, Pageable pageable);
}
