package com.sweethome.backend.controller;

import com.sweethome.backend.service.UserService;
import com.sweethome.backend.service.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable UUID id) {
        logger.info("Request to get user with id: {}", id);

        UserDto response = userService.getUser(id);

        logger.info("Successfully retrieved user with id: {}", id);
        return ResponseEntity.ok(response);
    }
}
