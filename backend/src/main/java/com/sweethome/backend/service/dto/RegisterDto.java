package com.sweethome.backend.service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class RegisterDto {
    @Email
    private String email;
    private String phoneNumber;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public RegisterDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public RegisterDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public RegisterDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public RegisterDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
