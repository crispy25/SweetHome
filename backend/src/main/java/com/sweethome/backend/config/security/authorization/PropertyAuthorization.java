package com.sweethome.backend.config.security.authorization;

import com.sweethome.backend.entity.Property;
import com.sweethome.backend.entity.User;
import com.sweethome.backend.exception.ResourceNotFoundException;
import com.sweethome.backend.repository.PropertyRepository;
import com.sweethome.backend.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("propertyAuthorization")
public class PropertyAuthorization {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    public PropertyAuthorization(PropertyRepository propertyRepository,
                            UserRepository userRepository) {
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
    }

    public boolean canModifyProperty(UUID propertyId) {
        final UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final boolean isAdmin = userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));

        if (isAdmin)
            return true;

        final Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new ResourceNotFoundException("Property", "id", propertyId));

        final User user = userRepository.findUserByEmail(userDetails.getUsername()).orElseThrow(() -> new ResourceNotFoundException("User", "email", userDetails.getUsername()));

        return property.getSeller().getId().equals(user.getId());
    }
}
