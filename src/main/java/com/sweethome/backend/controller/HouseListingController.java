package com.sweethome.backend.controller;

import com.sweethome.backend.service.HouseListingService;
import com.sweethome.backend.service.dto.HouseListingDto;
import com.sweethome.backend.service.dto.HouseListingPatchDto;
import com.sweethome.backend.service.dto.HouseListingResponseDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/houses")
public class HouseListingController extends PropertyListingController<HouseListingDto, HouseListingPatchDto, HouseListingResponseDto> {
    private final HouseListingService service;

    public HouseListingController(HouseListingService service) {
        this.service = service;
    }

    @Override
    protected HouseListingService getService() {
        return service;
    }
}
