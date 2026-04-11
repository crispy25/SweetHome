package com.sweethome.backend.service.dto;

import com.sweethome.backend.constants.ApartmentLayout;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApartmentResponseDto extends PropertyResponseDto {

    private Integer comfort;
    private Integer floor;
    private ApartmentLayout layout;
}
