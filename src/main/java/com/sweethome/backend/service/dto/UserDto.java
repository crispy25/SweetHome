package com.sweethome.backend.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@Component
public class UserDto {

    private UUID id;
    private String username;
    private String email;
}
