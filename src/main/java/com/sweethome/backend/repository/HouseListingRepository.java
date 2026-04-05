package com.sweethome.backend.repository;

import com.sweethome.backend.entity.HouseListing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HouseListingRepository extends JpaRepository<HouseListing, UUID> {
}
