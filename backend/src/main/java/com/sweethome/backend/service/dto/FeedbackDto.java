package com.sweethome.backend.service.dto;


import com.sweethome.backend.enums.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "5")
    @Min(1)
    @Max(5)
    private Integer rating;
    @Schema(example = "BUYER")
    private UserType userType;
    @Schema(example = "This is a very cool website!")
    @NotBlank
    private String message;
    @Schema(example = "[1, 2, 4]")
    private Set<Integer> feedbackOptionIds;
}
