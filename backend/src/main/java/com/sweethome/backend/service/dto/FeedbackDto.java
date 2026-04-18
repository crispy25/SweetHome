package com.sweethome.backend.service.dto;


import com.sweethome.backend.constants.UserType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDto {
    @Min(1)
    @Max(5)
    private Integer rating;
    private UserType userType;
    @NotBlank
    private String message;
    private Set<Integer> feedbackOptionIds;
}
