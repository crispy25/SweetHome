package com.sweethome.backend.controller;

import com.sweethome.backend.service.HouseListingService;
import com.sweethome.backend.service.PropertyListingService;
import com.sweethome.backend.service.dto.HouseListingDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/houses")
public class HouseListingController extends PropertyListingController<HouseListingDto> {
    private final HouseListingService service;

    public HouseListingController(HouseListingService service) {
        this.service = service;
    }

    @Override
    protected PropertyListingService<?, HouseListingDto> getService() {
        return service;
    }
}
