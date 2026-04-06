package com.sweethome.backend.service.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UserDto {
    private UUID id;
    private String username;
    private String email;

    public UserDto setId(UUID id) {
        this.id = id;
        return this;
    }

    public UserDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserDto setEmail(String email) {
        this.email = email;
        return this;
    }
}
