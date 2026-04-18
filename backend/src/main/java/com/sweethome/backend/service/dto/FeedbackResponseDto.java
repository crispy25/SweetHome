package com.sweethome.backend.service.dto;

import com.sweethome.backend.constants.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackResponseDto {
    private Integer rating;
    private UserType userType;
    private String message;
    private Set<Integer> feedbackOptions;
    private Instant createdAt;
    private UserDto user;
}
