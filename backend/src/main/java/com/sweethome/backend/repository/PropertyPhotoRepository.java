package com.sweethome.backend.repository;

import com.sweethome.backend.entity.PropertyPhoto;
import com.sweethome.backend.entity.PropertyPhotoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PropertyPhotoRepository extends JpaRepository<PropertyPhoto, PropertyPhotoId> {
    List<PropertyPhoto> findByIdPropertyId(UUID propertyId);
}
