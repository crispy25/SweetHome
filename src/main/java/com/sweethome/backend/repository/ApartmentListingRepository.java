package com.sweethome.backend.repository;

import com.sweethome.backend.entity.ApartmentListing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApartmentListingRepository extends JpaRepository<ApartmentListing, UUID>  {
}
