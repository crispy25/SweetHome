package com.sweethome.backend.service;

import com.sweethome.backend.entity.User;
import com.sweethome.backend.exception.ResourceNotFoundException;
import com.sweethome.backend.repository.UserRepository;
import com.sweethome.backend.service.dto.UserDto;
import com.sweethome.backend.service.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto getUser(UUID id) {
        final User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User",  "id", id));
        return userMapper.toDto(user);
    }
}
