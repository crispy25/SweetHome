package com.sweethome.backend.service.mapper;

import com.sweethome.backend.entity.Apartment;
import com.sweethome.backend.entity.House;
import com.sweethome.backend.entity.Property;
import com.sweethome.backend.service.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PropertyMapper {

    @SubclassMapping(source = House.class, target = HouseResponseDto.class)
    @SubclassMapping(source = Apartment.class, target = ApartmentResponseDto.class)
    public abstract PropertyResponseDto toDto(Property property);
}
