package com.sweethome.backend.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserDto {

    private UUID id;
    private String username;
    private String email;
    private String phoneNumber;
    private List<String> roles;
}
