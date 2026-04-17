package com.sweethome.backend.service;

import com.sweethome.backend.entity.PropertyPhoto;
import com.sweethome.backend.entity.PropertyPhotoId;
import com.sweethome.backend.repository.PropertyPhotoRepository;

import java.util.List;
import java.util.UUID;

public abstract class AbstractPropertyService {

    protected final PropertyPhotoRepository photoRepository;

    protected AbstractPropertyService(PropertyPhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    protected void savePhotos(List<String> photoUrls, UUID propertyId) {
        if (photoUrls != null && !photoUrls.isEmpty()) {
            final List<PropertyPhoto> photos = photoUrls.stream()
                    .map(url -> {
                        PropertyPhoto p = new PropertyPhoto();
                        p.setId(new PropertyPhotoId(propertyId, url));
                        return p;
                    })
                    .toList();
            photoRepository.saveAll(photos);
        }
    }

    protected List<String> getPhotos(UUID propertyId) {
        return photoRepository.findByIdPropertyId(propertyId).stream()
                .map(photo -> photo.getId().getPhotoUrl())
                .toList();
    }
}
