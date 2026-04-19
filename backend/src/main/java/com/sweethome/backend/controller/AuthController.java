package com.sweethome.backend.controller;

import com.sweethome.backend.service.AuthService;
import com.sweethome.backend.service.CurrentUserService;
import com.sweethome.backend.service.dto.LoginDto;
import com.sweethome.backend.service.dto.LoginResponseDto;
import com.sweethome.backend.service.dto.RegisterDto;
import com.sweethome.backend.service.mapper.UserMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private CurrentUserService currentUserService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginResponseDto loginResponseDto;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping(path ="/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        logger.info("Request to register user {}", registerDto.getEmail());
        authService.register(registerDto);
        logger.info("Successfully registered user {}", registerDto.getEmail());
        return new ResponseEntity<>("User registered", HttpStatus.CREATED);
    }

    @RequestMapping(path ="/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        logger.info("Request to login for user {}", loginDto.getEmail());
        String token = authService.login(loginDto);
        loginResponseDto.setToken(token).setUser(userMapper.toDto(currentUserService.getCurrentUser()));
        logger.info("Successfully logged in user {}", loginDto.getEmail());
        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @RequestMapping(path ="/token", method = RequestMethod.GET)
    public ResponseEntity<?> validateToken() {
        logger.info("Request to validate token");
        UUID id = currentUserService.getCurrentUserId();
        logger.info("Successfully validated token for user {}", id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
