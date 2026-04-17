package com.sweethome.backend.service;

import com.sweethome.backend.entity.User;
import com.sweethome.backend.exception.BadRequestException;
import com.sweethome.backend.exception.ResourceNotFoundException;
import com.sweethome.backend.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails userDetails))
            throw new BadRequestException("Unauthorized access");

        final String email = userDetails.getUsername();
        return userRepository.findUserByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User",  "email", email));
    }

    public UUID getCurrentUserId() {
        return getCurrentUser().getId();
    }
}
