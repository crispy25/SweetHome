package com.sweethome.backend.controller;

import com.sweethome.backend.service.ApartmentListingService;
import com.sweethome.backend.service.dto.ApartmentListingDto;
import com.sweethome.backend.service.dto.ApartmentListingPatchDto;
import com.sweethome.backend.service.dto.ApartmentListingResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/apartments")
public class ApartmentListingController extends PropertyListingController<ApartmentListingDto, ApartmentListingPatchDto, ApartmentListingResponseDto> {
    private final ApartmentListingService service;

    @Autowired
    public ApartmentListingController(ApartmentListingService service) {
        this.service = service;
    }
    
    @Override
    protected ApartmentListingService getService() {
        return service;
    }
}
