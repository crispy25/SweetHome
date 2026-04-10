package com.sweethome.backend.service.dto;

import com.sweethome.backend.constants.ApartmentLayout;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ApartmentResponseDto extends PropertyResponseDto {

    private Integer comfort;
    private Integer floor;
    private ApartmentLayout layout;
}
